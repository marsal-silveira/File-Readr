package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;

/**
 *
 */
public interface ReadrCommand {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    String command();
    String description();

    default String fullDescription() {

        return this.command() + " - " + this.description();
    }

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    CommandResponse exec(String input, ReadrFile file) throws InvalidInputException;
}