package br.com.marsalsilveira.readr.service.file.contracts;

import java.util.List;

/**
 *
 */
public interface ReadrRecord {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    List<ReadrField> fields();
}