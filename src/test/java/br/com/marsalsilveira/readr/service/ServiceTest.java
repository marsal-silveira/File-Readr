package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.exception.InvalidFileException;
import br.com.marsalsilveira.readr.exception.InvalidInputException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidFileException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommands() {

        List<String> commands = Arrays.asList(
                "count * - Return total amount of records in file.",
                "count distinct [property] - Count total values of given property.",
                "filter [property] [value] - Given a property, return all rows that matches the search query."
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
            String response = "File has 5565 record(s).";
            Assert.assertEquals(response, _service.execCommand("count *").messages().get(0));
            Assert.assertEquals(response, _service.execCommand(" CoUnt  *").messages().get(0));
        } catch (InvalidInputException e) {
            Assert.fail();
        }
    }

    @Test
    public void testExecCommandCountDistinct() {

        try {
            String response = "The field [uf] has [27] distinct value(s)";
            Assert.assertEquals(response, _service.execCommand("count distinct uf").messages().get(0));
            Assert.assertEquals(response, _service.execCommand(" CoUnt  DisTinCt Uf").messages().get(0));
        } catch (InvalidInputException e) {
            Assert.fail();
        }
    }

    @Test
    public void testExecCommandFilterPropertyValue() {

        try {
            String response = "1 results found for the query: filter [%s] = [%s]\n";
            Assert.assertEquals(String.format(response, "name", "são josé"), _service.execCommand("filter name são josé").messages().get(0));
            Assert.assertEquals(String.format(response, "name", "sao jose"), _service.execCommand("filter name sao jose").messages().get(0));
            Assert.assertEquals(String.format(response, "name", "são josé"), _service.execCommand(" fILTer  namE sÃo JosÉ").messages().get(0));
        } catch (InvalidInputException e) {
            Assert.fail();
        }
    }

    @Test(expected = InvalidInputException.class)
    public void testExecCommandInvalidInputException() throws InvalidInputException {

        _service.execCommand("[count *]");
    }
}