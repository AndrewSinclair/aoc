<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:msxsl="urn:schemas-microsoft-com:xslt" exclude-result-prefixes="msxsl"
>
  <xsl:output method="xml" indent="yes"/>

  <xsl:template match="@* | node()">
      <xsl:copy>
          <xsl:apply-templates select="@* | node()"/>
      </xsl:copy>
  </xsl:template>


  <xsl:template match="input">
    <output>
      <xsl:call-template name="count-parens">
        <xsl:with-param name="parens" select="text()"/>
      </xsl:call-template>
    </output>
  </xsl:template>
  

  <xsl:template name="count-parens">
    <xsl:param name="parens"/>

    <xsl:choose>
      <xsl:when test="string-length($parens) > 0">
        <xsl:variable name="paren-value">
          <xsl:choose>
            <xsl:when test="substring($parens, 1, 1) = '('">
              <xsl:value-of select="1"/>
            </xsl:when>
            <xsl:otherwise>
              <xsl:value-of select="-1"/>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:variable>

        <xsl:variable name="recur-value">
          <xsl:call-template name="count-parens">
            <xsl:with-param name="parens" select="substring($parens, 2)"/>
          </xsl:call-template>
        </xsl:variable>

        <xsl:value-of select="$paren-value + $recur-value"/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="0"/>
      </xsl:otherwise>
    </xsl:choose>

  </xsl:template>
</xsl:stylesheet>
