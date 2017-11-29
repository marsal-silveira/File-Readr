package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.console.ReadrConsole;
import br.com.marsalsilveira.readr.exception.InvalidInputException;
import br.com.marsalsilveira.readr.service.ReadrService;
import br.com.marsalsilveira.readr.service.ServicePool;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.utils.Strings;

/**
 * Main app controller responsible for manage all app flux and rules.
 */
public final class Readr {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final ReadrConsole _console;
    private final ReadrService _service = ServicePool.shared.getService();

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public Readr(ReadrConsole console) {

        _console = console;
    }

    //******************************************************************************************************************
    //* Run
    //******************************************************************************************************************

    public void run() {

        try {
            this.welcome();

            String input;
            do {

                // list all available command and wait for user input (command)
                this.commands();
                input = _console.input();

                // if user type `exit` just close program...
                if (!input.equals(Strings.exit)) {

                    try {

                        CommandResponse response = _service.execCommand(input);
                        response.messages().forEach(message -> _console.print(message));
                    } catch (InvalidInputException e) {

                        _console.print("Invalid command.");
                    }
                }
            } while (!input.equals(Strings.exit));

        } finally {

            _console.print("Finishing Readr...");
            _console.close();
        }
    }

    //******************************************************************************************************************
    //* Header
    //******************************************************************************************************************

    private void welcome() {

        _console.print(Strings.welcome);
        _console.print(Strings.description);
        _console.print();
        _console.print(Strings.version);
        // TODO: hardcoded... check a way to get this info from maven pom file...
        _console.print("1.0-SNAPSHOT");
    }

    private void commands() {

        _console.print();
        _console.print("----------------------------------------------------");
        _console.print(Strings.commands1);
        _service.commands().forEach(command -> _console.print(command));
        // `exit` command is managed by this controller... so it's hardcoded
        _console.print(Strings.exit);
        _console.print();
        _console.print(Strings.commands2);
    }
}