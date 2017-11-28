package br.com.marsalsilveira.readr.service.command.command;

import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;
import br.com.marsalsilveira.readr.utils.CollectionUtils;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.List;

/**
 *
 */
public class CountAll implements ReadrCommand {

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

    public CountAll() {

        _command = "count *";
        _description = "Return total amount of records in file.";
    }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    public CommandResponse exec(String input, ReadrFile file) throws InvalidInputException {

        if (!CountAll.Validator.isValid(input)) {

            throw new InvalidInputException();
        }

        String result = String.format("File has %d record(s).", file.count());

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
                && (parts.size() == 2)
                && (parts.get(0).equals("count"))
                && (parts.get(1).equals("*"));
        }
    }
}