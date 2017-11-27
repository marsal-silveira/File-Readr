package br.com.marsalsilveira.readr.service.file.csv;

import br.com.marsalsilveira.readr.service.file.FileType;
import br.com.marsalsilveira.readr.service.file.contracts.ReadrField;
import br.com.marsalsilveira.readr.service.file.contracts.ReadrFile;
import br.com.marsalsilveira.readr.service.file.contracts.ReadrRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
public class CsvFile implements ReadrFile {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final FileType _type;
    public FileType type() { return _type; }

    private final String _name;
    public String name() { return _name; }

    private final String _path;
    public String path() { return _path; }

    private final long _count;
    public long count() { return _count; }

    private final List<ReadrField> _fields;
    public List<ReadrField> fields() { return _fields; }

    public List<ReadrRecord> records() { return this.getRecords(); }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CsvFile(String path) {

        int nameIndex = path.lastIndexOf("/")+1;
        String name = path.substring(nameIndex).toLowerCase();

        _type = FileType.csv;
        _name = name;
        _path = path;
        _count = this.getRecordsCount();
        _fields = new ArrayList<>();

        this.loadFields();
    }

    //******************************************************************************************************************
    //* Fields and records
    //******************************************************************************************************************

    private long getRecordsCount() {

        long count = 0;
        try (Stream<String> lines = Files.lines(Paths.get(_path))) {

            count = lines
                .skip(1)
                .count();
        } catch (IOException e) {

            // we ignore this error because all files validation was done before...
        }
        return count;
    }

    private void loadFields() {

        // check if file is empty...
        File file = new File(_path);
        if (file.length() == 0) {

            return;
        }

        // extract first line (fields definition) and all records
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {

            String firstLine = reader.readLine();
            int index = -1;

            List<String> fieldNames = Arrays.asList(firstLine.split(","));
            for (String fieldName : fieldNames) {

                index++;
                _fields.add(new CsvField(index, fieldName));
            }
        } catch (IOException ex) {

            // we ignore this error because all files validation was done before...
            return;
        }
    }

    private List<ReadrRecord> getRecords() {

        List<ReadrRecord> records = new ArrayList<>();

        // check if file is empty...
        File file = new File(_path);
        if (file.length() == 0) {

            return records;
        }

        // extract all records (except first line)
        try (BufferedReader reader = Files.newBufferedReader(file.toPath())) {

            // skip first line
            reader.lines().skip(1).forEach(line -> {

                CsvRecord record = new CsvRecord(records.size());
                int index = -1;

                List<String> values = Arrays.asList(line.split(","));
                for (String value: values) {

                    index++;
                    CsvField field = ((CsvField) _fields.get(index));
                    record.addField(index, field.name(), value);
                }

                records.add(record);
            });
        } catch (IOException e) {

            return new ArrayList<>();
        }

        System.out.println("records.count -> " + records.size());
        return records;
    }
}