package br.com.marsalsilveira.readr.service.file;

import br.com.marsalsilveira.readr.service.file.contracts.ReadrFile;
import br.com.marsalsilveira.readr.service.file.csv.CsvFile;
import br.com.marsalsilveira.readr.utils.StringUtils;

/**
 *
 */
public final class FileFactory {

    // Avoid init this class...
    private FileFactory() { }

    //******************************************************************************************************************
    //* Factory
    //******************************************************************************************************************

    public static ReadrFile createFile(String fileName) {

        // TODO: raise an exception if fileName is empty
        if (StringUtils.isEmpty(fileName)) {

            return null;
        }

        String extension = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        FileType type = FileType.valueOf(extension);

        ReadrFile file;
        switch (type) {

            case csv:

                file = new CsvFile(fileName);
                break;

            default:

                file = null;
                break;
        }
        return file;
    }
}