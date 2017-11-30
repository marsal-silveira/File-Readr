package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
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

    public static final class Strings {

        // we put these strings here instead `Strings` because Strings should be independent from commands...
        // so these strings will break this principle.
        public static String command = "count distinct [property]";
        public static String description = "Return the number of distinct values of a given [property].";
        public static String fullDescription = command + " -> " + description;
        public static String response = "File has `%d` distinct value(s) for field `%s`.";

        private static String invalidField = "count distinct COMMAND -> invalid field `%s`";
        private static String tooManyFields = "count distinct COMMAND -> too many fields `%s`";
        private static String fieldNotFound = "count distinct COMMAND -> field not found";
    }

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    public String command() { return CountDistinct.Strings.command; }
    public String description() { return CountDistinct.Strings.description; }
    public String fullDescription() { return CountDistinct.Strings.fullDescription; }

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public CountDistinct() { }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    public CommandResponse exec(String input, ReadrFile file) throws InvalidCommandException {

        if (!CountDistinct.Validator.isValid(input)) {

            throw new InvalidCommandException();
        }

        List<String> parts = CollectionUtils.toList(input.toLowerCase());

        // check field...

        if (parts.size() > 3) {

            // join all fields parts into another one. eg. `name` and `uf`
            String fields = StringUtils.toString(parts, 2);
            throw new InvalidCommandException(String.format(CountDistinct.Strings.tooManyFields, fields));
        }

        // check if field has been given...
        String fieldName = parts.size() == 3 ? parts.get(2).toLowerCase() : null;
        if (StringUtils.isEmpty(fieldName)) {

            throw new InvalidCommandException(CountDistinct.Strings.fieldNotFound);
        }

        // check if field is valid...
        if (!file.fields().contains(fieldName)) {

            throw new InvalidCommandException(String.format(CountDistinct.Strings.invalidField, parts.get(2)));
        }

        int count = file.records()
                .stream()
                .map(record -> record.fieldByName(fieldName))
                .map(ReadrField::value)
                .distinct()
                .collect(Collectors.toList())
                .size();
        String result = String.format(CountDistinct.Strings.response, count, fieldName);

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
            return (parts.size() >= 2)
                && (parts.get(0).equals("count"))
                && (parts.get(1).equals("distinct"));
        }
    }
}