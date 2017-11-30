package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
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

    public static final class Strings {

        // we put these strings here instead `Strings` because Strings should be independent from commands...
        // so these strings will break this principle.
        public static String command = "filter [property] [value]";
        public static String description = "Return all records when given a [property] its value is equals to [value].";
        public static String fullDescription = command + " -> " + description;
        public static String response = "File has %d record(s) with field `%s` equals `%s`.";

        private static String invalidField = "filter COMMAND -> invalid field `%s`";
        private static String valueNotFound = "filter COMMAND -> value not found";
    }

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    public String command() { return FilterPropertyValue.Strings.command; }
    public String description() { return FilterPropertyValue.Strings.description; }
    public String fullDescription() { return FilterPropertyValue.Strings.fullDescription; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public FilterPropertyValue() { }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    public CommandResponse exec(String input, ReadrFile file) throws InvalidCommandException {

        if (!FilterPropertyValue.Validator.isValid(input)) {

            throw new InvalidCommandException();
        }

        // check if field and value are valid...
        List<String> parts = CollectionUtils.toList(input);

        // check if the field name is valid...
        String fieldName = parts.get(1).toLowerCase();
        if (!file.fields().contains(fieldName)) {

            throw new InvalidCommandException(String.format(FilterPropertyValue.Strings.invalidField, parts.get(1)));
        }

        // join all parts of value filter to another one... eg. `são` `josé` -> `são josé`
        String fieldValue = StringUtils.toString(parts, 2);

        // check if the field value has been given...
        if (StringUtils.isEmpty(fieldValue)) {

            throw new InvalidCommandException(FilterPropertyValue.Strings.valueNotFound);
        }

        List<String> records = file.records()
                .stream()
                .filter(record -> StringUtils.stripAccents(record.fieldByName(fieldName).value()).toLowerCase().equals(StringUtils.stripAccents(fieldValue).toLowerCase()))
                .map(ReadrRecord::valuesToString)
                .collect(Collectors.toList());

        CommandResponse response = new CommandResponse();
        response.addMessage(String.format(FilterPropertyValue.Strings.response, records.size(), fieldName, fieldValue));

        if (records.size() > 0) {

            response.addMessage(br.com.marsalsilveira.readr.utils.Strings.lineBreak + file.fieldsToString());
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
            return (parts.size() >= 2)
                && (parts.get(0).equals("filter"))
                && (StringUtils.isNotEmpty(parts.get(1)));
        }
    }
}