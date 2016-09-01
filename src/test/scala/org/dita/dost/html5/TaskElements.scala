package org.dita.dost.html5

class TaskElements extends Html5Specification {
    val cmd = <cmd class="- topic/ph task/cmd "> ... </cmd>
    val paragraph = <p class="- topic/p "> ... </p>

    "Task body" >> {
        applying {
            <taskbody class="- topic/body task/taskbody " id="taskbody">
                {paragraph}
            </taskbody>
        } must produce(
            <div class="body taskbody" id="taskbody">
                <p class="p"> ... </p>
            </div>
        )
    }

    "Prerequisites" >> {
        applying {
            <prereq class="- topic/section task/prereq " id="prereq">
                {paragraph}
            </prereq>
        } must produce(
            <section class="section prereq" id="prereq">
                <p class="p"> ... </p>
            </section>
        )
    }

    "Context" >> {
        applying {
            <context class="- topic/section task/context " id="context">
                {paragraph}
            </context>
        } must produce(
            <section class="section context" id="context">
                <p class="p"> ... </p>
            </section>
        )
    }

    "Steps" >> {
        applying {
            <steps class="- topic/ol task/steps ">
                <step class="- topic/li task/step ">
                    { cmd }
                </step>
            </steps>
        } must produce(
            <ol class="ol steps">
                <li class="li step">
                    <span class="ph cmd"> ... </span>
                </li>
            </ol>
        )
    }

    "Step" >> {
        "optional" in {
            applying {
                <step class="- topic/li task/step " importance="optional">
                    { cmd }
                </step>
            } must produce(
                <li class="li step step--optional">
                    <span class="ph cmd"> ... </span>
                </li>
            )
        }

        "required" in {
            applying {
                <step class="- topic/li task/step " importance="required">
                    { cmd }
                </step>
            } must produce(
                <li class="li step step--required">
                    <span class="ph cmd"> ... </span>
                </li>
            )
        }
    }

    "Step section" >> {
        applying {
            <stepsection class="- topic/li task/stepsection "> ... </stepsection>
        } must produce(
            <li class="li stepsection"> ... </li>
        )
    }
}
