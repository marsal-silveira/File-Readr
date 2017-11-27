package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.service.command.contracts.ReadrCommand;
import br.com.marsalsilveira.readr.service.command.countall.CountAll;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CommandProvider {

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    // Avoid initiate it outside
    private CommandProvider() { }

    //******************************************************************************************************************
    //* Commands
    //******************************************************************************************************************

    // TODO: review name...
    public static List<ReadrCommand> commands() {

        List<ReadrCommand> commands = new ArrayList<>();
        commands.add(new CountAll());
        // put here all others commands

        return commands;
    }

    public static ReadrCommand command(String input) {

        // TODO: review this... return a exception when input is invalid
        if (StringUtils.isEmpty(input)) {

            throw new RuntimeException("empty input...");
        }

        if (CountAll.Validator.isValid(input)) {

            return new CountAll();
        }

        return null;
    }
}