package gvprojects.simpledate;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: rohrj
 * Date: 1/9/13
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleDateTest {
    @Test
    public void testConstructor() {
        SimpleDate date = new SimpleDate(3, 4, 2012);
        date.getDay();
        Assert.assertEquals(4, date.getDay());
        Assert.assertEquals(3, date.getMonth());
        Assert.assertEquals(2012, date.getYear());
    }
}
