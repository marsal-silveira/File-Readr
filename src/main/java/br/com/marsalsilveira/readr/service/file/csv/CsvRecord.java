package br.com.marsalsilveira.readr.service.file.csv;

import br.com.marsalsilveira.readr.service.file.contracts.ReadrField;
import br.com.marsalsilveira.readr.service.file.contracts.ReadrRecord;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CsvRecord implements ReadrRecord {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final List<ReadrField> _fields;
    public List<ReadrField> fields() { return _fields; }

    private final int _index;
    public int index() { return _index; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CsvRecord(int index) {

        _fields = new ArrayList<>();
        _index = index;
    }

    //******************************************************************************************************************
    //* Utils
    //******************************************************************************************************************

    public void addField(CsvField field) {

        _fields.add(field);
    }

    public void addField(int index, String name, String value) {

        _fields.add(new CsvField(index, name, value));
    }
}