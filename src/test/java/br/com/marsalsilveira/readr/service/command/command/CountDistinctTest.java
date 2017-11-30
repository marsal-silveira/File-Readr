package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.service.file.FileMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for CountDistinct Command.
 */
public class CountDistinctTest {

    private CountDistinct _countDistinct;

    @Before
    public void setup() {

        _countDistinct = new CountDistinct();
    }

    @Test
    public void testProperties() {

        Assert.assertEquals(CountDistinct.Strings.command, _countDistinct.command());
        Assert.assertEquals(CountDistinct.Strings.description, _countDistinct.description());
        Assert.assertEquals(CountDistinct.Strings.fullDescription, _countDistinct.fullDescription());
    }

    @Test
    public void testValidation() {

        Assert.assertTrue(CountDistinct.Validator.isValid("count distinct uf"));
        Assert.assertTrue(CountDistinct.Validator.isValid(" CoUnt  DisTinCt Uf "));
        Assert.assertTrue(CountDistinct.Validator.isValid("count distinct"));
        Assert.assertTrue(CountDistinct.Validator.isValid("count distinct ufc"));
        Assert.assertTrue(CountDistinct.Validator.isValid("count distinct uf sc"));
        Assert.assertFalse(CountDistinct.Validator.isValid("count *"));
        Assert.assertFalse(CountDistinct.Validator.isValid("count distinc uf sc"));
    }

    @Test
    public void testExecOK() {

        try {

            Assert.assertEquals(String.format(CountDistinct.Strings.response, 1, "uf"), _countDistinct.exec("count distinct uf", new FileMock()).toString());
            Assert.assertEquals(String.format(CountDistinct.Strings.response, 1, "uf"), _countDistinct.exec(" CoUnt  DisTinCt Uf ", new FileMock()).toString());
            Assert.assertEquals(String.format(CountDistinct.Strings.response, 8, "microregion"), _countDistinct.exec("count distinct microregion", new FileMock()).toString());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = InvalidCommandException.class)
    public void testExecFail() throws InvalidCommandException {

        _countDistinct.exec("count distinct", new FileMock());
        _countDistinct.exec("count distinct ufc", new FileMock());
        _countDistinct.exec("count distinct uf pr", new FileMock());
    }
}