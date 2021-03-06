package org.displaytag.jsptests;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.displaytag.test.DisplaytagCase;
import org.displaytag.test.KnownTypes;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;


/**
 * Test for DISPL-252 - Multiple (chained) column decorators
 * @author Fabrizio Giustina
 * @author Sodara Hang
 * @version $Id$
 */
public class Displ252Test extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "DISPL-252.jsp";
    }

    /**
     * Decorated object based on a pageContext attribute
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
        Assert.assertEquals("Wrong number of tables.", 1, tables.length);

        Assert.assertEquals("Value not decorated as expected", "decorated: "
            + new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new KnownTypes().getTime()), tables[0].getCellAsText(1, 0));

    }
}