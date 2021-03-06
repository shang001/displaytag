package org.displaytag.jsptests;

import org.displaytag.test.DisplaytagCase;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


/**
 * Basic tests for pagination.
 * @author Fabrizio Giustina
 * @version $Revision: 1159 $ ($Author: fgiust $)
 */
public class PartialListSortFullTest extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "partialListSortFull.jsp";
    }

    /**
     * Verifies that the generated page contains the pagination links with the inupt parameter. Tests #917200 ("{}" in
     * parameters).
     * @param jspName jsp name, with full path
     * @throws Exception any axception thrown during test.
     */
    @Test
    public void doTest() throws Exception
    {

        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));

        WebResponse response = null;

        try
        {
            response = runner.getResponse(request);
            Assert.fail("Should have thrown an exception, its invalid to have sort=full when partialList=true");
        }
        catch (Throwable t)
        {
        }

        if (log.isDebugEnabled() && response != null)
        {
            log.debug("RESPONSE: " + response.getText());
        }
    }
}