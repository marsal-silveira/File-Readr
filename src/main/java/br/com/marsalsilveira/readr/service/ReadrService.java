package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.InvalidInputException;
import br.com.marsalsilveira.readr.service.file.InvalidFileException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Service main class.
 */
public interface ReadrService {

    //******************************************************************************************************************
    //* Setup
    //******************************************************************************************************************

    void setup(String filePath) throws FileNotFoundException, InvalidFileException;

    //******************************************************************************************************************
    //* Service
    //******************************************************************************************************************

    List<String> commands();
    List<String> fields();
    CommandResponse execCommand(String input) throws InvalidInputException;
}