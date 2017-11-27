package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.service.file.ReadrFile;

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