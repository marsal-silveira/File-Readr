package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.service.file.FileMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for FilterPropertyValue Command.
 */
public class FilterPropertyValueTest {

    private FilterPropertyValue _filterPropertyValue;

    @Before
    public void setup() {

        _filterPropertyValue = new FilterPropertyValue();
    }

    @Test
    public void testProperties() {

        Assert.assertEquals(FilterPropertyValue.Strings.command, _filterPropertyValue.command());
        Assert.assertEquals(FilterPropertyValue.Strings.description, _filterPropertyValue.description());
        Assert.assertEquals(FilterPropertyValue.Strings.fullDescription, _filterPropertyValue.fullDescription());
    }

    @Test
    public void testValidation() {

        Assert.assertTrue(FilterPropertyValue.Validator.isValid("filter name palhoça"));
        Assert.assertTrue(FilterPropertyValue.Validator.isValid(" filter  name Palhoca "));
        Assert.assertTrue(FilterPropertyValue.Validator.isValid(" filter  name sao jose dos pinhais"));
        Assert.assertTrue(FilterPropertyValue.Validator.isValid("filter name"));
        Assert.assertTrue(FilterPropertyValue.Validator.isValid("filter nome"));
        Assert.assertFalse(FilterPropertyValue.Validator.isValid("count *"));
    }

    @Test
    public void testExecOK() {

        try {

            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "Agrolândia"), _filterPropertyValue.exec("filter name Agrolândia", new FileMock()).messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "agrolândia"), _filterPropertyValue.exec("filter name agrolândia", new FileMock()).messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "AGROLÂNDIA"), _filterPropertyValue.exec("filter name AGROLÂNDIA", new FileMock()).messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 0, "name", "são josé"), _filterPropertyValue.exec("filter name são josé", new FileMock()).messages().get(0));
        } catch (InvalidCommandException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = InvalidCommandException.class)
    public void testExecFail() throws InvalidCommandException {

        _filterPropertyValue.exec("filter name", new FileMock());
    }
}