package org.dita.dost.html5

class ProgrammingDomain extends Html5Specification {
    "API name" >> {
        applying {
            <apiname class="- pr-d/apiname "> ... </apiname>
        } must produce(
            <code class="apiname"> ... </code>
        )
    }

    "Code block" >> {
        applying {
            <codeblock class="- pr-d/codeblock " outputclass="haskell">
                foo.bar()
            </codeblock>
        } must produce(
            <pre class="codeblock haskell">
                <code>
                    foo.bar()
                </code>
            </pre>
        )
    }

    "Code phrase" >> {
        applying {
            <codeph class="- pr-d/codeph "> ... </codeph>
        } must produce(
            <code class="codeph"> ... </code>
        )
    }

    "Option" >> {
        applying {
            <option class="- pr-d/option "> ... </option>
        } must produce(
            <var class="option"> ... </var>
        )
    }

    "Parameter name" >> {
        applying {
            <parmname class="- pr-d/parmname "> ... </parmname>
        } must produce(
            <var class="parmname"> ... </var>
        )
    }

    "Parameter list" >> {
        applying {
            <parml class="- pr-d/parml ">
                <plentry class="- pr-d/plentry ">
                    <pt class="- pr-d/pt ">foo</pt>
                    <pd class="- pr-d/pd ">bar</pd>
                </plentry>
                <plentry class="- pr-d/plentry ">
                    <pt class="- pr-d/pt ">baz</pt>
                    <pd class="- pr-d/pd ">quux</pd>
                </plentry>
            </parml>
        } must produce(
            <dl class="parml">
                <dt class="pt">foo</dt>
                <dd class="pd">bar</dd>
                <dt class="pt">baz</dt>
                <dd class="pd">quux</dd>
            </dl>
        )
    }

    "Syntax phrase" >> {
        "without child elements" in {
            applying {
                <synph class="- pr-d/synph "> ... </synph>
            } must produce(
                <code class="synph"> ... </code>
            )
        }

        "with child elements" in {
            applying {
                <synph class="- pr-d/synph ">
                    <kwd class=" pr-d/kwd ">kwd</kwd>
                    <var class=" pr-d/var ">var</var>
                    <oper class=" pr-d/oper ">oper</oper>
                    <delim class=" pr-d/delim ">delim</delim>
                    <sep class=" pr-d/sep ">sep</sep>
                </synph>
            } must produce(
                <code class="synph">
                    <var class="kwd">kwd</var>
                    <var class="var">var</var>
                    <var class="oper">oper</var>
                    <var class="delim">delim</var>
                    <var class="sep">sep</var>
                </code>
            )
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
        } must produce(
            <div class="syntaxdiagram" id="syntaxdiagram"/>
        )
    }.pendingUntilFixed("The way a syntax diagram should be expressed in HTML5 needs to be defined.")
}
