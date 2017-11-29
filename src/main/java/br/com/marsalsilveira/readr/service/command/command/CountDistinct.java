package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.model.ReadrField;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;
import br.com.marsalsilveira.readr.utils.CollectionUtils;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class CountDistinct implements ReadrCommand {

    //******************************************************************************************************************
    //* Strings
    //******************************************************************************************************************

    // we put these strings here instead `Strings` because Strings should be independent from commands...
    // so these strings will break this principle.
    private static String command = "count distinct [property]";
    private static String description = "Return the number of distinct values of a given [property].";
    public static String fullDescription = command + " -> " + description;
    public static String response= "File has `%d` distinct value(s) for field `%s`.";

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    public String command() { return CountDistinct.command; }
    public String description() { return CountDistinct.description; }
    public String fullDescription() { return CountDistinct.fullDescription; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CountDistinct() { }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    public CommandResponse exec(String input, ReadrFile file) throws InvalidInputException {

        if (!CountDistinct.Validator.isValid(input)) {

            throw new InvalidInputException();
        }

        // check if the property to be find is valid...
        List<String> parts = CollectionUtils.toList(input.toLowerCase());
        String fieldName = file.fieldByName(parts.get(2));
        if (fieldName == null) {

            throw new InvalidInputException();
        }

        int count = file.records()
                .stream()
                .map(record -> record.fieldByName(fieldName))
                .map(ReadrField::value)
                .distinct()
                .collect(Collectors.toList())
                .size();
        String result = String.format(CountDistinct.response, count, fieldName);

        CommandResponse response = new CommandResponse();
        response.addMessage(result);
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
            return (parts.size() == 3)
                && (parts.get(0).equals("count"))
                && (parts.get(1).equals("distinct"))
                && (StringUtils.isNotEmpty(parts.get(2)));
        }
    }
}