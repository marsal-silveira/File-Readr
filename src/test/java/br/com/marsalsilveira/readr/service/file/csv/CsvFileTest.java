package br.com.marsalsilveira.readr.service.file.csv;

import br.com.marsalsilveira.readr.exception.InvalidFileException;
import br.com.marsalsilveira.readr.service.file.FileType;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;

/**
 * Unit test for CsvFile.
 */
public class CsvFileTest {

    private static List<String> _fields = Arrays.asList(
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

    @Test
    public void testSetupFileFull() {

        String filePath = "src/test/resources/cidades.csv";
        try {
            CsvFile file = new CsvFile(filePath);

            // name, path, type
            Assert.assertEquals("cidades.csv", file.name());
            Assert.assertEquals(filePath, file.path());
            Assert.assertEquals(FileType.csv, file.type());
            Assert.assertFalse(file.isEmpty());

            // Fields and records
            Assert.assertThat(_fields, is(file.fields()));
            Assert.assertEquals(5565, file.count());
            Assert.assertEquals(5565, file.records().size());
        } catch (FileNotFoundException | InvalidFileException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testSetupFileWithoutRecords() {

        String filePath = "src/test/resources/cidades_without_records.csv";
        try {
            CsvFile file = new CsvFile(filePath);

            // name, path, type
            Assert.assertEquals("cidades_without_records.csv", file.name());
            Assert.assertEquals(filePath, file.path());
            Assert.assertEquals(FileType.csv, file.type());
            Assert.assertTrue(file.isEmpty());

            // Fields and records
            Assert.assertThat(_fields, is(file.fields()));
            Assert.assertEquals(0, file.count());
            Assert.assertEquals(0, file.records().size());
        } catch (FileNotFoundException | InvalidFileException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testSetupFileNotFound() throws FileNotFoundException {

        String filePath = "src/test/resources/cidades_fake.csv";
        try {
            CsvFile file = new CsvFile(filePath);
        } catch (InvalidFileException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = InvalidFileException.class)
    public void testSetupFileEmpty() throws InvalidFileException {

        String filePath = "src/test/resources/cidades_empty.csv";
        try {
            CsvFile file = new CsvFile(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
