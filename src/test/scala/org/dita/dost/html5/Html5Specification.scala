package org.dita.dost.html5

import org.specs2.mutable.Specification
import com.github.eerohele.expek.XsltSpecification

abstract class Html5Specification extends Specification with XsltSpecification {
    val stylesheet = XSLT.file(
        new java.io.File(
            System.getProperty("plugins.html5.dir"), "xsl/dita2html5Impl.xsl"
        )
    )
}
