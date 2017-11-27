package br.com.marsalsilveira.readr.service.file.csv;

import br.com.marsalsilveira.readr.service.file.ReadrField;

/**
 *
 */
public class CsvField implements ReadrField {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final int _index;
    public int index() { return _index; }

    private final String _name;
    public String name() { return _name; }

    private final String _value;
    public String value() { return _value; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CsvField(int index, String name, String value) {

        _index = index;
        _name = name;
        _value = value;
    }

    public CsvField(int index, String name) {

        this(index, name, null);
    }
}