<?xml version="1.0" encoding="UTF-8"?>
<!--
This file is part of the DITA Open Toolkit project.
See the accompanying license.txt file for applicable licenses.
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  xmlns:fo="http://www.w3.org/1999/XSL/Format"
  xmlns:svg="http://www.w3.org/2000/svg"
  xmlns:dita-ot="http://dita-ot.sourceforge.net/ns/201007/dita-ot"
  exclude-result-prefixes="xs dita-ot"
  version="2.0">

  <xsl:template match="*[contains(@class, ' svg-d/svg-container ')]">
    <fo:instream-foreign-object>
      <xsl:call-template name="commonattributes"/>
      <xsl:apply-templates/>
    </fo:instream-foreign-object>
  </xsl:template>

  <xsl:template match="svg:svg">
    <xsl:sequence select="."/>
  </xsl:template>

  <xsl:template match="*[contains(@class, ' svg-d/svgref ')][normalize-space(@href)]">
    <xsl:sequence
      select="dita-ot:retrieve-href-target(@href, xs:anyURI($input.dir.url))"/>
  </xsl:template>

  <xsl:template match="*[contains(@class, ' svg-d/svgref ')][empty(@href)]">
    <!-- TODO: Error message, @keyref support -->
  </xsl:template>

</xsl:stylesheet>
