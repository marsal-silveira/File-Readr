package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.contracts.ReadrConsole;
import br.com.marsalsilveira.readr.service.Service;
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
    private final Service _service = Service.shared;

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

                this.commands();
                input = _console.input();

                if (!input.equals(Strings.exit)) {

                    CommandResponse response = _service.execCommand(input);
                    _console.print(response.response());
                }
            } while (!input.equals(Strings.exit));

        } finally {

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
        // `exit` command is hardcoded...
        _console.print(Strings.exit);
        _console.print();
        _console.print(Strings.commands2);
    }
}