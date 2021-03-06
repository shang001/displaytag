package org.displaytag.jsptests;

import org.displaytag.test.DisplaytagCase;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;


/**
 * Tests for DISPL-18 - Setting own comparator for column sorting.
 * @author rapruitt
 * @version $Revision: 1159 $ ($Author: fgiust $)
 */
public class Displ018Test extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "DISPL-018.jsp";
    }

    /**
     * Check sorted columns.
     * @param jspName jsp name, with full path
     * @throws Exception any axception thrown during test.
     */
    @Test
    public void doTest() throws Exception
    {
        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));
        WebResponse response = runner.getResponse(request);

        if (log.isDebugEnabled())
        {
            log.debug(response.getText());
        }

        WebTable[] tables = response.getTables();
        Assert.assertEquals("Wrong number of tables.", 4, tables.length);

        for (int j = 0; j < tables.length; j++)
        {
            WebTable table = tables[j];
            Assert.assertEquals("Wrong number of columns in table." + j, 2, table.getColumnCount());
            Assert.assertEquals("Wrong number of rows in table." + j, 5, table.getRowCount());

            for (int u = 1; u < 5; u++)
            {
                Assert.assertEquals("Wrong value in table cell.", Integer.toString(u), table.getCellAsText(u, 1));
            }
        }

    }

}