package org.dita.dost.html5

class ProgrammingDomain extends Html5Specification {
    override val inputSchema = getSchema("technicalContent/xsd/topic.xsd")

    "API name" >> {
        applying {
            <apiname> ... </apiname>
        } must produce {
            <code class="apiname"> ... </code>
        }
    }

    "Code block" >> {
        applying {
            <codeblock outputclass="javascript">
                foo.bar()
            </codeblock>
        } must produce {
            <pre class="codeblock javascript">
                <code>
                    foo.bar()
                </code>
            </pre>
        }
    }

    "Code phrase" >> {
        applying {
            <codeph> ... </codeph>
        } must produce {
            <code class="codeph"> ... </code>
        }
    }

    "Option" >> {
        applying {
            <option> ... </option>
        } must produce {
            <var class="option"> ... </var>
        }
    }

    "Parameter name" >> {
        applying {
            <parmname> ... </parmname>
        } must produce {
            <var class="parmname"> ... </var>
        }
    }

    "Parameter list" >> {
        applying {
            <parml>
                <plentry>
                    <pt>foo</pt>
                    <pd>bar</pd>
                </plentry>
                <plentry>
                    <pt>baz</pt>
                    <pd>quux</pd>
                </plentry>
            </parml>
        } must produce {
            <dl class="parml">
                <dt class="pt">foo</dt>
                <dd class="pd">bar</dd>
                <dt class="pt">baz</dt>
                <dd class="pd">quux</dd>
            </dl>
        }
    }

    "Syntax phrase" >> {
        "without child elements" in {
            applying {
                <synph> ... </synph>
            } must produce {
                <code class="synph"> ... </code>
            }
        }

        "with child elements" in {
            applying {
                <synph>
                    <kwd>kwd</kwd>
                    <var>var</var>
                    <oper>oper</oper>
                    <delim>delim</delim>
                    <sep>sep</sep>
                </synph>
            } must produce {
                <code class="synph">
                    <var class="kwd">kwd</var>
                    <var class="var">var</var>
                    <var class="oper">oper</var>
                    <var class="delim">delim</var>
                    <var class="sep">sep</var>
                </code>
            }
        }
    }

    "Syntax diagram" >> {
        applying {
            <syntaxdiagram id="syntaxdiagram">
                <title>CopyFile</title>
                <groupseq><kwd>COPYF</kwd></groupseq>
                <groupcomp><var>input-filename</var><kwd>*INFILE</kwd></groupcomp>
                <groupseq><var>output-filename</var><kwd>*OUTFILE</kwd></groupseq>
                <groupchoice><var>input-filename</var><kwd>*INFILE</kwd></groupchoice>
                <groupchoice><var>output-filename</var><kwd>*OUTFILE</kwd></groupchoice>
            </syntaxdiagram>
        } must produce {
            <div class="syntaxdiagram" id="syntaxdiagram"/>
        } pendingUntilFixed
    }
}
