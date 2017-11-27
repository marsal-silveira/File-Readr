package br.com.marsalsilveira.readr.service.file.contracts;

import br.com.marsalsilveira.readr.service.file.FileType;

import java.util.List;

/**
 *
 */
public interface ReadrFile {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    FileType type();
    String name();
    String path();
    long count();
    List<ReadrField> fields();
    List<ReadrRecord> records();

    //******************************************************************************************************************
    //* Default Behavior
    //******************************************************************************************************************

    default boolean isEmpty() {

        return this.count() == 0;
    }
}