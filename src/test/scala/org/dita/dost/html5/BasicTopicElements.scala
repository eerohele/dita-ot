package org.dita.dost.html5

class BasicTopicElements extends Html5Specification {
    "Short description" >> {
        applying {
            <shortdesc class="- topic/shortdesc "> ... </shortdesc>
        } must produce(
            <p class="shortdesc"> ... </p>
        )
    }


    "Abstract" >> {
        "without short description" in {
            applying {
                <abstract class="- topic/abstract "> ... </abstract>
            } must produce(
                <div class="abstract"> ... </div>
            )
        }

        "with phrase-level short description" in {
            applying {
                <abstract class="- topic/abstract ">
                    foo
                    <shortdesc class="- topic/shortdesc ">shortdesc</shortdesc>
                    bar
                </abstract>
            } must produce(
                <div class="abstract">
                    foo
                    <span class="shortdesc">shortdesc</span>
                    bar
                </div>
            )
        }

        "with block-level short description" in {
            applying {
                <abstract class="- topic/abstract ">
                    <p class="- topic/p ">foo</p>
                    <shortdesc class="- topic/shortdesc ">shortdesc</shortdesc>
                    <p class="- topic/p ">bar</p>
                </abstract>
            } must produce(
                <div class="abstract">
                    <p class="p">foo</p>
                    <p class="shortdesc">shortdesc</p>
                    <p class="p">bar</p>
                </div>
            )
        }
    }

    "Body" >> {
        applying {
            <body class="- topic/body "> ... </body>
        } must produce(
            <div class="body"> ... </div>
        )
    }

    "Bodydiv" >> {
        applying {
            <bodydiv class="- topic/bodydiv "> ... </bodydiv>
        } must produce(
            <div class="bodydiv"> ... </div>
        )
    }

    "Related links" >> {
        applying {
            <related-links class="- topic/related-links " scope="external" format="html">
                <link class="- topic/link " href="http://www.example.org">
                    <linktext class="- topic/linktext ">Example 1</linktext>
                </link>
            </related-links>
        } must produce(
            <nav class="related-links" role="navigation">
                <h1>Related information</h1>
                <ul class="linklist relinfo">
                    <li>
                        <a class="link" href="http://www.example.html">Example 1</a>
                    </li>
                </ul>
            </nav>
        )
    }

}
