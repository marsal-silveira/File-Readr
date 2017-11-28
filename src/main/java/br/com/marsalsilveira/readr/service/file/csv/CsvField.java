package br.com.marsalsilveira.readr.service.file.csv;

import br.com.marsalsilveira.readr.service.file.ReadrField;

/**
 *
 */
public class CsvField implements ReadrField {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final String _name;
    public String name() { return _name; }

    private final String _value;
    public String value() { return _value; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CsvField(String name, String value) {

        _name = name;
        _value = value;
    }
}