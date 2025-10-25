<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match="/">
        <html>
            <head>
                <title>Mobile Phone Sales Summary</title>
                <style>
                    body { font-family: Arial, sans-serif; }
                    table { border-collapse: collapse; width: 80%; margin: 20px auto; }
                    th, td { border: 1px solid black; padding: 8px; text-align: left; }
                    th { background-color: #f2f2f2; }
                    .red-text { color: red; font-weight: bold; } /* Style for the condition */
                </style>
            </head>
            <body>
                <h2 style="text-align: center;">Mobile Phone Sales Summary (Weekly Report)</h2>
                
                <table>
                    <thead>
                        <tr>
                            <th>Model ID</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Color</th>
                            <th>SIM Size</th>
                            <th>Memory</th>
                            <th>Camera</th>
                            <th>Touch Screen</th>
                            <th>Number Sold</th>
                            <th>Store Name</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:apply-templates select="MobileSalesData/MobilePhoneSale"/>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="MobilePhoneSale">
        <tr>
            <td><xsl:value-of select="ModelID"/></td>
            <td><xsl:value-of select="Brand"/></td>
            <td><xsl:value-of select="Price"/></td>
            <td><xsl:value-of select="Color"/></td>
            <td><xsl:value-of select="SIMSize"/></td>
            <td><xsl:value-of select="Memory"/></td>
            <td><xsl:value-of select="Camera"/></td>
            <td><xsl:value-of select="TouchScreen"/></td>
            
            <td>
                <xsl:choose>
                    <xsl:when test="NoSold > 10">
                        <span class="red-text">
                            <xsl:value-of select="NoSold"/>
                        </span>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="NoSold"/>
                    </xsl:otherwise>
                </xsl:choose>
            </td>
            
            <td><xsl:value-of select="StoreName"/></td>
        </tr>
    </xsl:template>
</xsl:stylesheet>