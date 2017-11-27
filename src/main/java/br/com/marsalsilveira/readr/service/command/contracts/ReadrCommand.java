package br.com.marsalsilveira.readr.service.command.contracts;

import br.com.marsalsilveira.readr.service.file.contracts.ReadrFile;

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

    String exec(ReadrFile file);
}