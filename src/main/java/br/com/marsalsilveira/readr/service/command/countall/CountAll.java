package br.com.marsalsilveira.readr.service.command.countall;

import br.com.marsalsilveira.readr.service.command.contracts.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.contracts.ReadrFile;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.Arrays;

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

    public String exec(ReadrFile file) {

        return String.format("File has %d record(s).", file.count());
    }

    //******************************************************************************************************************
    //* Validator
    //******************************************************************************************************************

    public static final class Validator {

        // Avoid create it
        private Validator() { }

        //******************************************************************************************************************
        //* Validation
        //******************************************************************************************************************

        public static boolean isValid(String input) {

            // if input is empty return false...
            if (StringUtils.isEmpty(input)) {

                return false;
            }

            String[] parts = StringUtils.toArray(input);
            return (parts != null) && (parts .length == 2) && (parts[0].equals("count")) && (parts[1].equals("*"));
        }
    }
}