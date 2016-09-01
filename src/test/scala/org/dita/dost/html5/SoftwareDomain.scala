package org.dita.dost.html5

class SoftwareDomain extends Html5Specification {
    "Message phrase" >> {
        applying {
            <msgph class="- sw-d/msgph "> ... </msgph>
        } must produce(
            <samp class="msgph"> ... </samp>
        )
    }

    "Message block" >> {
        "without child elements" in {
            applying {
                <msgblock class="- sw-d/msgblock ">
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
                <msgblock class="- sw-d/msgblock ">
                    <msgnum class="- sw-d/msgnum ">A:1</msgnum><msgph class="- sw-d/msgph ">foo</msgph>
                    <msgnum class="- sw-d/msgnum ">B:2</msgnum><msgph class="- sw-d/msgph ">bar</msgph>
                    <msgnum class="- sw-d/msgnum ">C:3</msgnum><msgph class="- sw-d/msgph ">baz</msgph>
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
            <cmdname class="- sw-d/cmdname "> ... </cmdname>
        } must produce(
            <span class="cmdname"> ... </span>
        )
    }

    "Variable name" >> {
        applying {
            <varname class="- sw-d/varname "> ... </varname>
        } must produce(
            <var class="varname"> ... </var>
        )
    }

    "File path" >> {
        applying {
            <filepath class="- sw-d/filepath "> ... </filepath>
        } must produce(
            <span class="filepath"> ... </span>
        )
    }

    "User input" >> {
        applying {
            <userinput class="- sw-d/userinput "> ... </userinput>
        } must produce(
            <kbd class="userinput"> ... </kbd>
        )
    }

    "System output" >> {
        applying {
            <systemoutput class="- sw-d/systemoutput "> ... </systemoutput>
        } must produce(
            <samp class="systemoutput"> ... </samp>
        )
    }

}
