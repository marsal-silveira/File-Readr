package br.com.marsalsilveira.readr.service.command;

import br.com.marsalsilveira.readr.exception.CommandException;
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
    String fullDescription();

    //******************************************************************************************************************
    //* Execution
    //******************************************************************************************************************

    CommandResponse exec(String input, ReadrFile file) throws CommandException;
}