package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.service.command.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.ReadrFile;
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

            List<String> parts = CollectionUtils.toList(input);
            return (parts != null) && (parts.size() == 2) && (parts.get(0).equals("count")) && (parts.get(1).equals("*"));
        }
    }
}