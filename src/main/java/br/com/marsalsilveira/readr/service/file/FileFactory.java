package br.com.marsalsilveira.readr.service.file;

import br.com.marsalsilveira.readr.service.file.csv.CsvFile;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 */
public final class FileFactory {

    // Avoid create it
    private FileFactory() { }

    //******************************************************************************************************************
    //* Factory
    //******************************************************************************************************************

    public static ReadrFile createFile(String filePath) throws FileNotFoundException, InvalidFileException {

        FileFactory.validate(filePath);

        String extension = filePath.substring(filePath.lastIndexOf(".")+1).toLowerCase();
        FileType type = FileType.valueOf(extension);

        ReadrFile file;
        switch (type) {

            case csv:

                file = new CsvFile(filePath);
                break;

            default:

                file = null;
                break;
        }
        return file;
    }

    public static void validate(String filePath) throws FileNotFoundException, InvalidFileException {

        File file = new File(filePath);

        if (!file.exists()) {

            throw new FileNotFoundException(filePath);
        }

        if (StringUtils.isEmpty(filePath)) {

            throw new InvalidFileException("File: " + filePath + " exists but is invalid.");
        }
    }
}