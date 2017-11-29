package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;
import br.com.marsalsilveira.readr.utils.CollectionUtils;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class FilterPropertyValue implements ReadrCommand {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final String _command;
    public String command() { return _command; }

    private final String _description;
    public String description() { return _description; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public FilterPropertyValue() {

        _command = "filter [property] [value]";
        _description = "Given a property, return all rows that matches the search query.";
    }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    public CommandResponse exec(String input, ReadrFile file) throws InvalidInputException {

        if (!FilterPropertyValue.Validator.isValid(input)) {

            throw new InvalidInputException();
        }

        List<String> parts = CollectionUtils.toList(input.toLowerCase());
        String fieldName = parts.get(1);
        // joint all parts of value filter... eg. `são` `josé` are two parts and must be fusion in an unique part `são josé`
        String fieldValue = parts.stream().skip(2).reduce("", (v1, v2) -> v1 + (v1.equals("") ? "" : " ") + v2);

        // check if the property to be find is valid...
        if (!file.fields().contains(fieldName)) {

            throw new InvalidInputException("File doesn't contain `" + fieldName + "` property.");
        }

        List<String> records = file.records()
                .stream()
                .filter(record -> StringUtils.stripAccents(record.fieldByName(fieldName).value()).toLowerCase().equals(StringUtils.stripAccents(fieldValue)))
                .map(record -> record.valuesToString())
                .collect(Collectors.toList());

        CommandResponse response = new CommandResponse();
        response.addMessage(String.format("%d results found for the query: filter [%s] = [%s]\n", records.size(), fieldName, fieldValue));
        response.addMessage(file.fieldsToString());
        records.forEach(record -> response.addMessage(record));

        return response;
    }

    //******************************************************************************************************************
    //* Validator
    //******************************************************************************************************************

    public static final class Validator {

        // Avoid to create it
        private Validator() { }

        //******************************************************************************************************************
        //* Validation
        //******************************************************************************************************************

        public static boolean isValid(String input) {

            if (StringUtils.isEmpty(input)) {

                return false;
            }

            List<String> parts = CollectionUtils.toList(input.toLowerCase());
            return (parts != null)
                    && (parts.size() >= 3)
                    && (parts.get(0).equals("filter"))
                    && (StringUtils.isNotEmpty(parts.get(1)))
                    && (StringUtils.isNotEmpty(parts.get(2)));
        }
    }
}