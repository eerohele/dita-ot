package org.dita.dost.html5

class SoftwareDomain extends Html5Specification {
    override val inputSchema = getSchema("technicalContent/xsd/topic.xsd")

    "Message phrase" >> {
        applying {
            <msgph> ... </msgph>
        } must produce(
            <samp class="msgph"> ... </samp>
        )
    }

    "Message block" >> {
        "without child elements" in {
            applying {
                <msgblock>
                A:1 foo
                B:2 bar
                C:3 baz
                </msgblock>
            } must produce(
                <pre class="msgblock">
                A:1 foo
                B:2 bar
                C:3 baz
                </pre>
            )
        }

        "with child elements" in {
            applying {
                <msgblock>
                    <msgnum>A:1</msgnum><msgph>foo</msgph>
                    <msgnum>B:2</msgnum><msgph>bar</msgph>
                    <msgnum>C:3</msgnum><msgph>baz</msgph>
                </msgblock>
            } must produce(
                <pre class="msgblock">
                    <span class="msgnum">A:1</span><samp class="msgph">foo</samp>
                    <span class="msgnum">B:2</span><samp class="msgph">bar</samp>
                    <span class="msgnum">C:2</span><samp class="msgph">baz</samp>
                </pre>
            )
        }
    }

    "Command name" >> {
        applying {
            <cmdname> ... </cmdname>
        } must produce(
            <span class="cmdname"> ... </span>
        )
    }

    "Variable name" >> {
        applying {
            <varname> ... </varname>
        } must produce(
            <var class="varname"> ... </var>
        )
    }

    "File path" >> {
        applying {
            <filepath> ... </filepath>
        } must produce(
            <span class="filepath"> ... </span>
        )
    }

    "User input" >> {
        applying {
            <userinput> ... </userinput>
        } must produce(
            <kbd class="userinput"> ... </kbd>
        )
    }

    "System output" >> {
        applying {
            <systemoutput> ... </systemoutput>
        } must produce(
            <samp class="systemoutput"> ... </samp>
        )
    }

}
