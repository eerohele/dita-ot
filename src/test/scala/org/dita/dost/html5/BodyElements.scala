package org.dita.dost.html5

class BodyElements extends Html5Specification {
    override val inputSchema = getSchema("technicalContent/xsd/topic.xsd")

    "Alternate text" >> {
        applying {
            <image href="foo.svg">
                <alt> ... </alt>
            </image>
        } must produce {
            <img class="image" src="foo.svg" alt=" ... "/>
        }
    }

    "Bibliographic citation" >> {
        applying {
            <cite> ... </cite>
        } must produce {
            <cite class="cite"> ... </cite>
        }
    }

    "Definition list" >> {
        "simple definition list" in {
            applying {
                <dl>
                    <dlentry>
                        <dt>dt</dt>
                        <dd>dd</dd>
                    </dlentry>
                </dl>
            } must produce {
                <dl class="dl">
                   <dt class="dt dlterm">dt</dt>
                   <dd class="dd">dd</dd>
                </dl>
            }
        }

        "definition list with a heading" in {
            applying {
                <dl>
                    <dlhead>
                        <dthd>dthd</dthd>
                        <ddhd>ddhd</ddhd>
                    </dlhead>
                    <dlentry>
                        <dt>dt</dt>
                        <dd>dd</dd>
                    </dlentry>
                </dl>
            } must produce {
                <dl class="dl">
                    <dt class="dthd">
                        <strong>dthd</strong>
                    </dt>
                    <dd class="ddhd">
                        <strong>ddhd</strong>
                    </dd>
                    <dt class="dt dlterm">dt</dt>
                    <dd class="dd">dd</dd>
                </dl>
            }
        }
    }

    "Draft comment" should {
        val draftComment =
            <draft-comment author="Jane Doe" time="2016-08-31" disposition="Open">
                draft-comment
            </draft-comment>

        "when draft mode is enabled" in {
            applying(draftComment)
                .withStylesheetParameters("DRAFT" -> "yes") must produce {
                <div class="draft-comment">
                    <header>
                        <h1>Draft comment</h1>
                        <h2>Open</h2>
                        <h3><time datetime="2016-08-31">2016-08-31</time></h3>
                    </header>

                    draft-comment
                </div>
            }
        }

        "when draft mode is disabled" in {
            applying(draftComment)
                .withStylesheetParameters("DRAFT" -> "no").result must beEmpty
        }
    }
}
