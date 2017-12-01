package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.CommandException;
import br.com.marsalsilveira.readr.service.file.FileMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for CountAll Command.
 */
public class CountAllTest {

    private CountAll _countAll;

    @Before
    public void setup() {

        _countAll = new CountAll();
    }

    @Test
    public void testProperties() {

        Assert.assertEquals(CountAll.Strings.command, _countAll.command());
        Assert.assertEquals(CountAll.Strings.description, _countAll.description());
        Assert.assertEquals(CountAll.Strings.fullDescription, _countAll.fullDescription());
    }

    @Test
    public void testValidation() {

        Assert.assertTrue(CountAll.Validator.isValid("count *"));
        Assert.assertTrue(CountAll.Validator.isValid(" cOuNt  * "));
        Assert.assertFalse(CountAll.Validator.isValid("count *;"));
    }

    @Test
    public void testExecOK() {

        try {

            String expected = String.format(CountAll.Strings.response, 10);
            Assert.assertEquals(expected, _countAll.exec("count *", new FileMock()).toString());
            Assert.assertEquals(expected, _countAll.exec(" cOuNt   * ", new FileMock()).toString());
        } catch (CommandException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = CommandException.class)
    public void testExecFail() throws CommandException {

        _countAll.exec("[count *]", new FileMock());
    }
}