package org.dita.dost.html5

import com.github.eerohele.expek.{XsltSpecification, ResolvingXMLLoader => Loader}

import net.sf.saxon.s9api.XdmNode
import scala.xml.{Elem, Node, XML}
import java.io.File

class Table extends Html5Specification {
    import com.github.eerohele.expek.NodeConversions._

    implicit def namespace = "http://dita-ot.sourceforge.net/ns/201007/dita-ot/table"

    lazy val testDir = System.getProperty("test.resources.dir")

    val row: Elem =
        <row class="- topic/row ">
            <entry class="- topic/entry " colname="c1" dita-ot:x="1" dita-ot:y="1"/>
            <entry class="- topic/entry " colname="c2" dita-ot:x="2" dita-ot:y="1"/>
        </row>

    def table(row: Elem, id: String = "table"): Elem = {
        <table class="- topic/table " xmlns:dita-ot="http://dita-ot.sourceforge.net/ns/201007/dita-ot" id={id}>
            <tgroup class="- topic/tgroup " cols="2">
                <colspec class="- topic/colspec " colname="c1"/>
                <colspec class="- topic/colspec " colname="c2"/>
                <tbody class="- topic/tbody ">
                    { row }
                </tbody>
            </tgroup>
        </table>
    }

    "Regular table" >> {
        applying(table(row)) must produce {
            <table class="table" id="table">
                <caption/>
                <colgroup>
                    <col/>
                    <col/>
                </colgroup>
                <tbody class="tbody">
                    <tr class="row">
                        <td class="entry"/>
                        <td class="entry"/>
                    </tr>
                </tbody>
            </table>
        }
    }

    "rowspan" >> {
        applying(query = "table/tgroup/tbody/row")(
            table(
                <row class="- topic/row ">
                    <entry class="- topic/entry " morerows="1" colname="c1" dita-ot:x="1"/>
                    <entry class="- topic/entry " colname="c2" dita-ot:x="1"/>
                </row>
            )
        ) must produce {
            <tr class="row">
                <td class="entry" rowspan="2"/>
                <td class="entry"/>
            </tr>
        }
    }

    "colspan" >> {
        applying(query = "table/tgroup/tbody/row")(
            table(
                <row class="- topic/row ">
                    <entry class="- topic/entry " colname="c1" dita-ot:morecols="1" dita-ot:x="1"/>
                </row>
            )
        ) must produce {
            <tr class="row">
                <td class="entry" colspan="2"/>
            </tr>
        }
    }

    "@rowheader" >> {
        applying(query = "table/tgroup/tbody/row")(
            XSLT.transform[XdmNode](
                XSLT.elem(
                    <xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
                        <xsl:template match="table">
                            <xsl:copy>
                                <xsl:sequence select="@*"/>
                                <xsl:attribute name="rowheader">firstcol</xsl:attribute>
                                <xsl:sequence select="*"/>
                            </xsl:copy>
                        </xsl:template>
                    </xsl:stylesheet>
                ), table(row)
            )
        ) must produce(
            <tr class="row">
                <th scope="row" class="entry"/>
                <td class="entry"/>
            </tr>
        )(filterAttr(!XPath.matches("@headers", _)))
    }

    "complex table" >> {
        applying("topic/body/table") {
            Loader.load(new File(testDir, "complexTable.dita"))
        } must produce { <table/> }
    }.pendingUntilFixed

    "dita-ot:get-current-table" should {
        "work when given table" in {
            callingFunction("get-current-table")(
                element(table(row))
            ) must produce { table(row) }
        }

        "work when given row" in {
            callingFunction("get-current-table")(
                XPath.select("tgroup/tbody/row")(element(table(row)))
            ) must produce { table(row) }
        }

        "work when given nested table" in {
            callingFunction("get-current-table")(
                XPath.select("tgroup/tbody/row/entry/table")(
                    element(
                        table(
                            <row class="- topic/row ">
                                <entry class="- topic/entry " colname="c1" dita-ot:x="1">
                                    { table(row, "table2") }
                                </entry>
                            </row>
                        )
                    )
                )
            ) must produce { table(row, "table2") }
        }
    }
}
