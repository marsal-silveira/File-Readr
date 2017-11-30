package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.exception.InvalidFileException;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * Unit test for ServicePool.
 */
public class ServicePoolTest {

    private void setup(String filePath) throws FileNotFoundException, InvalidFileException {

        ServicePool.shared.setup(new Service(filePath));
    }

    @Test
    public void testSetupOK() {

        try {
            this.setup("src/test/resources/cidades.csv");
            Assert.assertTrue(true);
        } catch (FileNotFoundException | InvalidFileException e) {

            e.printStackTrace();
        }
    }

    @Test
    public void testGetService() {

        try {

            this.setup("src/test/resources/cidades.csv");
            Assert.assertEquals(Service.class, ServicePool.shared.getService().getClass());
        } catch (InvalidFileException | FileNotFoundException e) {

            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testSetupFileNotFoundException() throws FileNotFoundException {

        try {

            this.setup("cidades.csv");
        } catch (InvalidFileException e) {

            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = InvalidFileException.class)
    public void testSetupEmptyFile() throws InvalidFileException {

        try {

            this.setup("src/test/resources/cidades_empty.csv");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test(expected = InvalidFileException.class)
    public void testSetupWithoutExtensionFile() throws InvalidFileException {

        try {

            this.setup("src/test/resources/cidades_without_extension");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            Assert.fail();
        }
    }
}