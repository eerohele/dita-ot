package org.dita.dost.html5

class BodyElements extends Html5Specification {
    "Alternate text" >> {
        applying {
            <image class="- topic/image " href="foo.svg">
                <alt class="- topic/alt "> ... </alt>
            </image>
        } must produce(
            <img class="image" src="foo.svg" alt=" ... "/>
        )
    }

    "Bibliographic citation" >> {
        applying {
            <cite class="- topic/cite "> ... </cite>
        } must produce(
            <cite class="cite"> ... </cite>
        )
    }

    "Definition list" >> {
        "simple definition list" in {
            applying {
                <dl class="- topic/dl ">
                    <dlentry class="- topic/dlentry ">
                        <dt class="- topic/dt ">dt</dt>
                        <dd class="- topic/dd ">dd</dd>
                    </dlentry>
                </dl>
            } must produce(
                <dl class="dl">
                   <dt class="dt dlterm">dt</dt>
                   <dd class="dd">dd</dd>
                </dl>
            )
        }

        "definition list with a heading" in {
            applying {
                <dl class="- topic/dl ">
                    <dlhead class="- topic/dlhead ">
                        <dthd class="- topic/dthd ">dthd</dthd>
                        <ddhd class="- topic/ddhd ">ddhd</ddhd>
                    </dlhead>
                    <dlentry class="- topic/dlentry ">
                        <dt class="- topic/dt ">dt</dt>
                        <dd class="- topic/dd ">dd</dd>
                    </dlentry>
                </dl>
            } must produce(
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
            )
        }
    }

    "Draft comment" should {
        val draftComment =
            <draft-comment class="- topic/draft-comment " author="Jane Doe" time="2016-08-31" disposition="Open">
                draft-comment
            </draft-comment>

        "when draft mode is enabled" in {
            applying(draftComment)
                .withStylesheetParameters("DRAFT" -> "yes") must produce(
                <div class="draft-comment">
                    <header>
                        <h1>Draft comment</h1>
                        <h2>Open</h2>
                        <h3><time datetime="2016-08-31">2016-08-31</time></h3>
                    </header>

                    draft-comment
                </div>
            )
        }

        "when draft mode is disabled" in {
            applying(draftComment)
                .withStylesheetParameters("DRAFT" -> "no").result must beEmpty
        }
    }

}
