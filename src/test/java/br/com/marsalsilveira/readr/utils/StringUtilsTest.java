package br.com.marsalsilveira.readr.utils;


import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for StringUtils.
 */
public class StringUtilsTest {

    @Test
    public void testIsEmpty() {

        Assert.assertEquals(true, StringUtils.isEmpty(null));
        Assert.assertEquals(true, StringUtils.isEmpty(""));
        Assert.assertEquals(true, StringUtils.isEmpty(" "));
        Assert.assertEquals(false, StringUtils.isEmpty("abc"));
        Assert.assertEquals(false, StringUtils.isEmpty(" abc "));
    }

    @Test
    public void testIsNotEmpty() {

        Assert.assertEquals(false, StringUtils.isNotEmpty(null));
        Assert.assertEquals(false, StringUtils.isNotEmpty(""));
        Assert.assertEquals(false, StringUtils.isNotEmpty(" "));
        Assert.assertEquals(true, StringUtils.isNotEmpty("abc"));
        Assert.assertEquals(true, StringUtils.isNotEmpty(" abc "));
    }
}