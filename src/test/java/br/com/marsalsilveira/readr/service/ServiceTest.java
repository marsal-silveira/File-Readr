package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.exception.InvalidFileException;
import br.com.marsalsilveira.readr.service.command.command.CountAll;
import br.com.marsalsilveira.readr.service.command.command.CountDistinct;
import br.com.marsalsilveira.readr.service.command.command.FilterPropertyValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Unit test for Service
 */
public class ServiceTest {

    private Service _service;

    @Before
    public void setup() {

        String filePath = "src/test/resources/cidades.csv";
        try {
            _service = new Service(filePath);
        } catch (FileNotFoundException | InvalidFileException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommands() {

        List<String> commands = Arrays.asList(
                CountAll.Strings.fullDescription,
                CountDistinct.Strings.fullDescription,
                FilterPropertyValue.Strings.fullDescription
        );
        Assert.assertThat(commands , is(_service.commands()));
    }

    @Test
    public void testFields() {

        List<String> fields = Arrays.asList(
                "ibge_id",
                "uf",
                "name",
                "capital",
                "lon",
                "lat",
                "no_accents",
                "alternative_names",
                "microregion",
                "mesoregion"
        );
        Assert.assertThat(fields , is(_service.fields()));
    }

    @Test
    public void testExecCommandCountAll() {

        try {
            String response = String.format(CountAll.Strings.response, 5565);
            Assert.assertEquals(response, _service.execCommand("count *").messages().get(0));
            Assert.assertEquals(response, _service.execCommand(" CoUnt  *").messages().get(0));
        } catch (InvalidCommandException e) {
            Assert.fail();
        }
    }

    @Test
    public void testExecCommandCountDistinct() {

        try {
            String response = String.format(CountDistinct.Strings.response, 27, "uf");
            Assert.assertEquals(response, _service.execCommand("count distinct uf").messages().get(0));
            Assert.assertEquals(response, _service.execCommand(" CoUnt  DisTinCt Uf").messages().get(0));
        } catch (InvalidCommandException e) {
            Assert.fail();
        }
    }

    @Test
    public void testExecCommandFilterPropertyValue() {

        try {
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "são josé"), _service.execCommand("filter name são josé").messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "SÃO JOSÉ"), _service.execCommand("filter name SÃO JOSÉ").messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "sao jose"), _service.execCommand("filter name sao jose").messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "SAO JOSE"), _service.execCommand("filter name SAO JOSE").messages().get(0));
            Assert.assertEquals(String.format(FilterPropertyValue.Strings.response, 1, "name", "sÃo JoSe"), _service.execCommand("  fILTer  namE sÃo    JoSe  ").messages().get(0));
        } catch (InvalidCommandException e) {
            Assert.fail();
        }
    }

    @Test(expected = InvalidCommandException.class)
    public void testExecCommandInvalidInputException() throws InvalidCommandException {

        _service.execCommand("[count *]");
    }
}