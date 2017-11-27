package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.service.file.ReadrField;
import br.com.marsalsilveira.readr.service.file.ReadrFile;
import br.com.marsalsilveira.readr.utils.CollectionUtils;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private ReadrField getFieldFromStream(Stream<ReadrField> stream, String fieldName) {

        return stream
                .filter(field -> field.name().equals(fieldName))
                .findFirst()
                .orElse(null);
//                .orElseThrow(InvalidInputException::new);
    }

    public CommandResponse exec(String input, ReadrFile file) throws InvalidInputException {

        if (!CountDistinct.Validator.isValid(input)) {

            throw new InvalidInputException();
        }

        List<String> parts = CollectionUtils.toList(input);
        String fieldName = parts.get(2);

        ReadrField queryField = this.getFieldFromStream(file.fields().stream(), fieldName);

        int distinct = file.records()
                .stream()
                .map(record -> this.getFieldFromStream(record.fields().stream(), fieldName))
                .map(field -> field.value())
                .distinct()
                .collect(Collectors.toList())
                .size();

        String result = String.format("The property [%s] has [%d] distinct value(s)", fieldName, distinct);
        return new CommandResponse(result);
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

            List<String> parts = CollectionUtils.toList(input);
            return (parts != null)
                && (parts.size() == 3)
                && (parts.get(0).equals("count"))
                && (parts.get(1).equals("distinct"))
                && (StringUtils.isNotEmpty(parts.get(2)));
        }
    }
}