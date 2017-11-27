package br.com.marsalsilveira.readr.service;

import br.com.marsalsilveira.readr.service.command.CommandProvider;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.service.command.contracts.ReadrCommand;
import br.com.marsalsilveira.readr.service.command.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.file.FileFactory;
import br.com.marsalsilveira.readr.service.file.contracts.ReadrFile;
import br.com.marsalsilveira.readr.service.file.exception.InvalidFileException;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service main class.
 */
public enum Service {

    // we didn't use UPPERCASED to looks like more `elegant` when call it. e.g: Service.shared.commands().
    shared;

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private ReadrFile _file = null;
    private boolean _initialized = false;

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    Service() {}

    //******************************************************************************************************************
    //* Setup | Assert
    //******************************************************************************************************************

    public void setup(String filePath) throws FileNotFoundException, InvalidFileException {

        _file = FileFactory.createFile(filePath);
        _initialized = true;
    }

    private void assertInitialized() {

        // if service isn't initialized throw a runtime exception to abort execution.
        // the program can't continue in this state.
        if (!_initialized) {

            throw new RuntimeException("Service isn't initialized. Please call `Service.shared.setup(filePath)` before use it.");
        }
    }

    //******************************************************************************************************************
    //* Services
    //******************************************************************************************************************

    public List<String> commands() {

        this.assertInitialized();

        return CommandProvider.commands().stream().map(command -> command.fullDescription()).collect(Collectors.toList());
    }

    public List<String> properties() {

        this.assertInitialized();

        return _file.fields().stream().map(property -> property.name()).collect(Collectors.toList());
    }

    public CommandResponse execCommand(String input) throws InvalidInputException {

        this.assertInitialized();

        if (StringUtils.isEmpty(input)) {

            throw new InvalidInputException("Input is empty");
        }

        ReadrCommand command = CommandProvider.command(input);
        CommandResponse response = new CommandResponse(command.exec(_file));
        return response;
    }
}