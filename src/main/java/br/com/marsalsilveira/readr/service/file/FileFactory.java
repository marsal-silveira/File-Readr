package br.com.marsalsilveira.readr.service.file;

import br.com.marsalsilveira.readr.exception.InvalidFileException;
import br.com.marsalsilveira.readr.service.file.csv.CsvFile;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;
import br.com.marsalsilveira.readr.utils.StringUtils;
import br.com.marsalsilveira.readr.utils.Strings;

import java.io.FileNotFoundException;

/**
 *
 */
public final class FileFactory {

    // Avoid to create it
    private FileFactory() { }

    //******************************************************************************************************************
    //* Factory
    //******************************************************************************************************************

    public static ReadrFile createFile(String filePath) throws FileNotFoundException, InvalidFileException {

        FileFactory.validate(filePath);

        // extract file extension...
        int lastIndex = filePath.lastIndexOf(".");
        if (lastIndex == -1) {

            throw new InvalidFileException(String.format(Strings.invalidFileExceptionExtensionNotFound, filePath));
        }
        String extension = filePath.substring(lastIndex+1).toLowerCase();
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

    private static void validate(String filePath) throws FileNotFoundException {

        if (StringUtils.isEmpty(filePath)) {

            throw new FileNotFoundException(Strings.fileNotFoundExceptionEmptyPath);
        }
    }
}