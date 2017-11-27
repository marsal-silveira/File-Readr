package br.com.marsalsilveira.readr.utils;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Unit test for StringUtils.
 */
public class StringUtilsTest {

    @Test
    public void testIsBlank() {

        Assert.assertEquals(true, StringUtils.isEmpty(null));
        Assert.assertEquals(true, StringUtils.isEmpty(""));
        Assert.assertEquals(true, StringUtils.isEmpty(" "));
        Assert.assertEquals(false, StringUtils.isEmpty("abc"));
        Assert.assertEquals(false, StringUtils.isEmpty(" abc "));
    }

    @Test
    public void testToArray() {

        // null or empty
        String[] nullOrEmpty = {};
        assertArrayEquals(nullOrEmpty, StringUtils.toArray(null));
        assertArrayEquals(nullOrEmpty, StringUtils.toArray(""));
        assertArrayEquals(nullOrEmpty, StringUtils.toArray(" "));

        // count *
        String[] count = {"count", "*"};
        assertArrayEquals(count, StringUtils.toArray("count *"));
        assertArrayEquals(count, StringUtils.toArray(" count  * "));

        // filter [property] [value]
        String[] filter = {"filter", "uf", "sc"};
        assertArrayEquals(filter, StringUtils.toArray("filter uf sc"));
        assertArrayEquals(filter, StringUtils.toArray(" filter  uf  sc "));
    }
}