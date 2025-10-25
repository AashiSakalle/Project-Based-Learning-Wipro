<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/FlowerDepot">
    <html>
      <head>
        <title>Flower Depot Inventory</title>
        <style>
          body { font-family: Arial, sans-serif; }
          table { border-collapse: collapse; width: 50%; margin-top: 20px; }
          th, td { border: 1px solid black; padding: 8px; text-align: left; }
          th { background-color: #f2f2f2; }
          .depot-header { font-size: 1.2em; font-weight: bold; margin-bottom: 10px; }
        </style>
      </head>
      <body>
        <div class="depot-header">Flower Depot</div>
        <table>
          <thead>
            <tr>
              <th>Flower Name</th>
              <th>Color</th>
              <th>Price</th>
              <th>Availability</th>
            </tr>
          </thead>
          <tbody>
            <xsl:for-each select="Flower[Availability = 'Yes']">
              <xsl:sort select="Name" data-type="text" order="ascending"/>
              <tr>
                <td><xsl:value-of select="Name"/></td>
                <td><xsl:value-of select="Color"/></td>
                <td><xsl:value-of select="Price"/></td>
                <td><xsl:value-of select="Availability"/></td>
              </tr>
            </xsl:for-each>
          </tbody>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>