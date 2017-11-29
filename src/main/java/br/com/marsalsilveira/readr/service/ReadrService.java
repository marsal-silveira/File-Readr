package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.service.command.CommandResponse;

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
    CommandResponse execCommand(String input) throws InvalidCommandException;
}