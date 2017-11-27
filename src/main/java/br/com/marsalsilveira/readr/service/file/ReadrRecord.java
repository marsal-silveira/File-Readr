package br.com.marsalsilveira.readr.service.file;

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