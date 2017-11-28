package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.exception.InvalidInputException;

import java.util.List;

/**
 *
 */
public interface ReadrService {

    //******************************************************************************************************************
    //* Service
    //******************************************************************************************************************

    List<String> commands();
    List<String> fields();
    CommandResponse execCommand(String input) throws InvalidInputException;
}