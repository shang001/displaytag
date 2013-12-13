package org.displaytag.jsptests;

import org.displaytag.test.DisplaytagCase;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;


/**
 * Tests for DISPL-2 - Ability to use java var in id attribute in tabletag.
 * @author Fabrizio Giustina
 * @version $Revision: 1159 $ ($Author: fgiust $)
 */
public class Displ002Test extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "DISPL-002.jsp";
    }

    /**
     * Check if tables are generated with variable id and content in column is filled appropriately.
     * @param jspName jsp name, with full path
     * @throws Exception any axception thrown during test.
     */
    @Test
    public void doTest() throws Exception
    {
        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));
        WebResponse response;

        response = runner.getResponse(request);

        if (log.isDebugEnabled())
        {
            log.debug(response.getText());
        }

        WebTable[] tables = response.getTables();
        Assert.assertEquals("Wrong number of tables in result.", 4, tables.length);

        for (int j = 0; j < tables.length; j++)
        {
            WebTable table = tables[j];
            Assert.assertEquals("Wrong number of rows in table " + (j + 1), 2, table.getRowCount());
            Assert.assertEquals("Wrong content in cell for table " + (j + 1), "ant", table.getCellAsText(1, 0));
            Assert.assertEquals("Wrong content in cell for table " + (j + 1), "bee", table.getCellAsText(1, 1));
        }
    }
}