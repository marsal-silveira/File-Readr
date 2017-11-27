package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.service.command.contracts.ReadrCommand;
import br.com.marsalsilveira.readr.service.command.countall.CountAll;
import br.com.marsalsilveira.readr.service.command.exception.InvalidInputException;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CommandProvider {

    // Avoid create it
    private CommandProvider() { }

    //******************************************************************************************************************
    //* Commands
    //******************************************************************************************************************

    public static List<ReadrCommand> commands() {

        List<ReadrCommand> commands = new ArrayList<>();
        commands.add(new CountAll());
        // put here all others commands

        return commands;
    }

    public static ReadrCommand command(String input) throws InvalidInputException {

        if (StringUtils.isEmpty(input)) {

            throw new InvalidInputException("Input is empty");
        }

        if (CountAll.Validator.isValid(input)) {

            return new CountAll();
        }

        throw new InvalidInputException();
    }
}