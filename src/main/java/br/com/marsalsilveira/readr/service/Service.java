package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.exception.InvalidFileException;
import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.service.command.CommandFactory;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.ReadrCommand;
import br.com.marsalsilveira.readr.service.file.FileFactory;
import br.com.marsalsilveira.readr.service.file.model.ReadrFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public final class Service implements ReadrService {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private ReadrFile _file = null;

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public Service(String filePath) throws FileNotFoundException, InvalidFileException {

        _file = FileFactory.createFile(filePath);
    }

    //******************************************************************************************************************
    //* Service
    //******************************************************************************************************************

    public List<String> commands() {

        // get all available command and parse it to string (full description)
        return CommandFactory.commands().stream().map(ReadrCommand::fullDescription).collect(Collectors.toList());
    }

    public List<String> fields() {

        return _file.fields();
    }

    public CommandResponse execCommand(String input) throws InvalidCommandException {

        ReadrCommand command = CommandFactory.command(input);
        return command.exec(input, _file);
    }
}