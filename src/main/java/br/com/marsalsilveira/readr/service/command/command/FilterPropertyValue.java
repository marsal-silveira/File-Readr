package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;
import br.com.marsalsilveira.readr.service.file.model.ReadrRecord;
import br.com.marsalsilveira.readr.utils.CollectionUtils;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class FilterPropertyValue implements ReadrCommand {

    //******************************************************************************************************************
    //* Strings
    //******************************************************************************************************************

    // we put these strings here instead `Strings` because Strings should be independent from commands...
    // so these strings will break this principle.
    private static String command = "filter [property] [value]";
    private static String description = "Return all records when given a [property] its value is equals to [value].";
    public static String fullDescription = command + " -> " + description;
    public static String response = "File has %d records with field `%s` equals `%s`.";

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    public String command() { return FilterPropertyValue.command; }
    public String description() { return FilterPropertyValue.description; }
    public String fullDescription() { return FilterPropertyValue.fullDescription; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public FilterPropertyValue() { }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    public CommandResponse exec(String input, ReadrFile file) throws InvalidInputException {

        if (!FilterPropertyValue.Validator.isValid(input)) {

            throw new InvalidInputException();
        }

        List<String> parts = CollectionUtils.toList(input);
        String fieldName = parts.get(1).toLowerCase();
        // join all parts of value filter... eg. `são` `josé` are two parts and must be fusion in an unique part `são josé`
        String fieldValue = parts.stream().skip(2).reduce("", (v1, v2) -> v1 + (v1.equals("") ? "" : " ") + v2);

        // check if the property to be find is valid...
        if (!file.fields().contains(fieldName)) {

            throw new InvalidInputException("File doesn't contain `" + fieldName + "` property.");
        }

        List<String> records = file.records()
                .stream()
                .filter(record -> StringUtils.stripAccents(record.fieldByName(fieldName).value()).toLowerCase().equals(StringUtils.stripAccents(fieldValue).toLowerCase()))
                .map(ReadrRecord::valuesToString)
                .collect(Collectors.toList());

        CommandResponse response = new CommandResponse();
        response.addMessage(String.format(FilterPropertyValue.response, records.size(), fieldName, fieldValue));

        if (records.size() > 0) {

            response.addMessage("\n" + file.fieldsToString());
            records.forEach(response::addMessage);
        }

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
            return  (parts.size() >= 3)
                    && (parts.get(0).equals("filter"))
                    && (StringUtils.isNotEmpty(parts.get(1)))
                    && (StringUtils.isNotEmpty(parts.get(2)));
        }
    }
}