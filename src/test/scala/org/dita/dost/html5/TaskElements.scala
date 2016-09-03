package org.dita.dost.html5

class TaskElements extends Html5Specification {
    override val inputSchema = getSchema("technicalContent/xsd/task.xsd")

    val cmd = <cmd class="- topic/ph task/cmd "> ... </cmd>
    val paragraph = <p class="- topic/p "> ... </p>

    "Task body" >> {
        applying {
            <taskbody id="taskbody"/>
        } must produce {
            <div class="body taskbody" id="taskbody"/>
        }
    }

    "Prerequisites" >> {
        applying {
            <prereq id="prereq">
                {paragraph}
            </prereq>
        } must produce {
            <section class="section prereq" id="prereq">
                <p class="p"> ... </p>
            </section>
        }
    }

    "Context" >> {
        applying {
            <context id="context">
                {paragraph}
            </context>
        } must produce {
            <section class="section context" id="context">
                <p class="p"> ... </p>
            </section>
        }
    }

    "Steps" >> {
        applying {
            <steps>
                <step>
                    { cmd }
                </step>
            </steps>
        } must produce {
            <ol class="ol steps">
                <li class="li step">
                    <span class="ph cmd"> ... </span>
                </li>
            </ol>
        }
    }

    "Step" >> {
        "optional" in {
            applying {
                <step importance="optional">
                    { cmd }
                </step>
            } must produce {
                <li class="li step step--optional">
                    <span class="ph cmd"> ... </span>
                </li>
            }
        }

        "required" in {
            applying {
                <step importance="required">
                    { cmd }
                </step>
            } must produce {
                <li class="li step step--required">
                    <span class="ph cmd"> ... </span>
                </li>
            }
        }
    }

    "Step section" >> {
        applying {
            <stepsection> ... </stepsection>
        } must produce {
            <li class="li stepsection"> ... </li>
        }
    }
}
