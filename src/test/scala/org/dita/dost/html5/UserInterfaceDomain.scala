package org.dita.dost.html5

class UserInterfaceDomain extends Html5Specification {
    "User interface control" >> {
        applying {
            <uicontrol class=" - ui-d/uicontrol "> ... </uicontrol>
        } must produce(
            <span class="uicontrol"> ... </span>
        )
    }

    "Menu cascade" >> {
        applying {
            <menucascade class=" - ui-d/menucascade " xml:lang="en">
                <uicontrol class=" - ui-d/uicontrol ">foo</uicontrol>
                <uicontrol class=" - ui-d/uicontrol ">bar</uicontrol>
            </menucascade>
        } must produce(
            <span class="menucascade" lang="en">
                <span class="uicontrol">foo</span>
                <abbr title="and then">&#160;&gt; </abbr>
                <span class="uicontrol">bar</span>
            </span>
        )
    }.pendingUntilFixed("https://github.com/dita-ot/dita-ot/issues/2446")

    "Screen" >> {
        applying {
            <screen class=" - ui-d/screen " id="screen" scale="90"> ... </screen>
        } must produce(<pre id="screen" class="screen scale-90"> ... </pre>)
    }

    "Shortcut" >> {
        applying {
            <shortcut class="- ui-d/shortcut "> ... </shortcut>
        } must produce(<span class="shortcut"> ... </span>)
    }

    "Window title" >> {
        applying {
            <wintitle class="- ui-d/wintitle "> ... </wintitle>
        } must produce(<span class="wintitle"> ... </span>)
    }
}
