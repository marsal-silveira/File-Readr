package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CommandProvider {

    // Avoid to create it
    private CommandProvider() { }

    //******************************************************************************************************************
    //* Commands
    //******************************************************************************************************************

    /**
     * Return all available commands.
     *
     * @return All available commands.
     */
    public static List<ReadrCommand> commands() {

        List<ReadrCommand> commands = new ArrayList<>();
        commands.add(new CountAll());
        commands.add(new CountDistinct());
        // put here others commands...

        return commands;
    }

    /**
     *
     *
     * @param input
     *
     * @return
     *
     * @throws InvalidInputException
     */
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

        throw new InvalidInputException();
    }
}