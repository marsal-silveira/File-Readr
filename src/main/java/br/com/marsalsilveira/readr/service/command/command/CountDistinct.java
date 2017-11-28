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
public class CountDistinct implements ReadrCommand {

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

    public CountDistinct() {

        _command = "count distinct [property]";
        _description = "Count total values of given property.";
    }

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
                .map(field -> field.value())
                .distinct()
                .collect(Collectors.toList())
                .size();
        String result = String.format("The field [%s] has [%d] distinct value(s)", fieldName, count);

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
            return (parts != null)
                && (parts.size() == 3)
                && (parts.get(0).equals("count"))
                && (parts.get(1).equals("distinct"))
                && (StringUtils.isNotEmpty(parts.get(2)));
        }
    }
}