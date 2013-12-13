package org.displaytag.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Test data provider. A list of 5 NumberedItems.
 * @author Fabrizio Giustina
 * @version $Revision: 1160 $ ($Author: fgiust $)
 */
public class KnownNumberedList
{

    /**
     * Returns an iterator on a list made of 5 NumberedItems objects.
     * @return iterator on a list made of 5 NumberedItems objects
     */
    public Iterator<NumberedItem> iterator()
    {
        List<NumberedItem> list = new ArrayList<NumberedItem>();
        for (int j = 0; j < 5; j++)
        {
            list.add(new NumberedItem(j));
        }
        return list.iterator();
    }
}