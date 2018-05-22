/*
 * This file is part of the DITA Open Toolkit project.
 *
 * Copyright 2018 Jarno Elovirta
 *
 * See the accompanying LICENSE file for applicable license.
 */
package org.dita.dost.writer;

import org.dita.dost.util.SaxCache;
import org.dita.dost.util.SaxCache.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.*;

import static org.dita.dost.util.Constants.ATTRIBUTE_NAME_OUTPUTCLASS;
import static org.dita.dost.util.Constants.PR_D_CODEBLOCK;

/**
 * Trim whitespace in codeblock elements.
 *
 * @since 3.1
 */
public final class NormalizeCodeblock extends AbstractXMLFilter {

    private Set<String> outputClass = new HashSet(Arrays.asList("normalize-space"));
    private int depth = 0;
    private Collection<SaxEvent> buf = new ArrayList<>();

    @Override
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        if (depth > 0) {
            buf.add(new StartPrefixMappingEvent(prefix, uri));
        } else {
            super.startPrefixMapping(prefix, uri);
        }
    }

    @Override
    public void endPrefixMapping(String prefix)
            throws SAXException {
        if (depth > 0) {
            buf.add(new EndPrefixMappingEvent(prefix));
        } else {
            super.endPrefixMapping(prefix);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes atts)
            throws SAXException {
        if (depth > 0) {
            depth++;
            buf.add(new StartElementEvent(uri, localName, qName, atts));
        } else if (PR_D_CODEBLOCK.matches(atts) && hasStripWhitespace(atts.getValue(ATTRIBUTE_NAME_OUTPUTCLASS))) {
            depth = 1;
            super.startElement(uri, localName, qName, atts);
        } else {
            super.startElement(uri, localName, qName, atts);
        }
    }

    private boolean hasStripWhitespace(String value) {
        return value != null && Arrays.stream(value.split("\\s+")).anyMatch(cls -> outputClass.contains(cls));
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (depth > 0) {
            depth--;
            if (depth == 0) {
                for (final SaxEvent event : normalizeSpace(buf)) {
                    event.write(getContentHandler());
                }
                buf.clear();
                super.endElement(uri, localName, qName);
            } else {
                buf.add(new EndElementEvent(uri, localName, qName));
            }
        } else {
            super.endElement(uri, localName, qName);
        }
    }

    private Collection<SaxEvent> normalizeSpace(Collection<SaxEvent> buf) {
        final int min = getMinimumIndent(buf);
        if (min == 0) {
            return buf;
        }
        final List<SaxEvent> res = new ArrayList<>(buf.size());
        boolean previousEndedInLinefeed = true;
        for (final SaxEvent event : buf) {
            if (event instanceof CharactersEvent) {
                final CharactersEvent e = (CharactersEvent) event;
                final char[] ch = stripLeadingSpace(previousEndedInLinefeed, min, e.ch, e.start, e.length);
                res.add(new CharactersEvent(ch, 0, ch.length));
                previousEndedInLinefeed = ch[ch.length - 1] == '\n';
            } else {
                res.add(event);
            }
        }
        return res;
    }

    private int getMinimumIndent(Collection<SaxEvent> buf) {
        final String merged = getCharacters(buf);
        return Arrays.stream(merged.split("\n"))
                .filter(str -> !str.isEmpty())
                .mapToInt(this::countLeadingSpace)
                .min()
                .orElse(0);
    }

    private String getCharacters(Collection<SaxEvent> buf) {
        final StringBuilder merged = new StringBuilder();
        for (final SaxEvent event : buf) {
            if (event instanceof CharactersEvent) {
                final CharactersEvent e = (CharactersEvent) event;
                merged.append(e.ch, e.start, e.length);
            }
        }
        return merged.toString();
    }

    char[] stripLeadingSpace(boolean first, int prefix, char[] ch, int start, int length) {
        final String str = first
                ? new String(ch, start + prefix, length - prefix)
                : new String(ch, start, length);
        return str
                .replaceAll("\n {" + prefix + "}", "\n")
                .toCharArray();
    }

    int countLeadingSpace(String ch) {
        return ch.replaceAll("^( *)\\S*", "$1").length();
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        if (depth > 0) {
            buf.add(new CharactersEvent(ch, start, length));
        } else {
            super.characters(ch, start, length);
        }
    }

    @Override
    public void ignorableWhitespace(char ch[], int start, int length)
            throws SAXException {
        if (depth > 0) {
            buf.add(new CharactersEvent(ch, start, length));
        } else {
            super.ignorableWhitespace(ch, start, length);
        }
    }

    @Override
    public void processingInstruction(String target, String data)
            throws SAXException {
        if (depth > 0) {
            buf.add(new ProcessingInstructionEvent(target, data));
        } else {
            super.processingInstruction(target, data);
        }
    }

}