package org.dita.dost.html5

class BasicTopicElements extends Html5Specification {
    override val inputSchema = getSchema("technicalContent/xsd/topic.xsd")

    "Short description" >> {
        applying {
            <shortdesc> ... </shortdesc>
        } must produce {
            <p class="shortdesc"> ... </p>
        }
    }

    "Abstract" >> {
        "without short description" in {
            applying {
                <abstract> ... </abstract>
            } must produce {
                <div class="abstract"> ... </div>
            }
        }

        "with phrase-level short description" in {
            applying {
                <abstract>
                    foo
                    <shortdesc>shortdesc</shortdesc>
                    bar
                </abstract>
            } must produce {
                <div class="abstract">
                    foo
                    <span class="shortdesc">shortdesc</span>
                    bar
                </div>
            }
        }

        "with block-level short description" in {
            applying {
                <abstract>
                    <p>foo</p>
                    <shortdesc>shortdesc</shortdesc>
                    <p>bar</p>
                </abstract>
            } must produce {
                <div class="abstract">
                    <p class="p">foo</p>
                    <p class="shortdesc">shortdesc</p>
                    <p class="p">bar</p>
                </div>
            }
        }
    }

    "Body" >> {
        applying { <body/> } must produce { <div class="body"/> }
    }

    "Bodydiv" >> {
        applying {
            <bodydiv> ... </bodydiv>
        } must produce {
            <div class="bodydiv"> ... </div>
        }
    }

    "Related links" >> {
        applying {
            <related-links scope="external" format="html">
                <link href="http://www.example.org">
                    <linktext>Example 1</linktext>
                </link>
            </related-links>
        } must produce {
            <nav class="related-links" role="navigation">
                <h1>Related information</h1>
                <ul class="linklist relinfo">
                    <li>
                        <a class="link" href="http://www.example.html">Example 1</a>
                    </li>
                </ul>
            </nav>
        }
    }

}
