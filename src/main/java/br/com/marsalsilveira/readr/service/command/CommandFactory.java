package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.command.command.CountAll;
import br.com.marsalsilveira.readr.service.command.command.CountDistinct;
import br.com.marsalsilveira.readr.service.command.command.FilterPropertyValue;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CommandFactory {

    // Avoid to create it
    private CommandFactory() { }

    //******************************************************************************************************************
    //* Commands
    //******************************************************************************************************************

    public static List<ReadrCommand> commands() {

        List<ReadrCommand> commands = new ArrayList<>();
        commands.add(new CountAll());
        commands.add(new CountDistinct());
        commands.add(new FilterPropertyValue());
        // put here others command...

        return commands;
    }

    public static ReadrCommand command(String input) throws InvalidInputException {

        if (StringUtils.isEmpty(input)) {

            throw new InvalidInputException("Input is empty.");
        }

        if (CountAll.Validator.isValid(input)) {

            return new CountAll();
        }

        if (CountDistinct.Validator.isValid(input)) {

            return new CountDistinct();
        }

        if (FilterPropertyValue.Validator.isValid(input)) {

            return new FilterPropertyValue();
        }

        throw new InvalidInputException();
    }
}