package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.console.ReadrConsole;
import br.com.marsalsilveira.readr.exception.InvalidCommandException;
import br.com.marsalsilveira.readr.service.ReadrService;
import br.com.marsalsilveira.readr.service.ServicePool;
import br.com.marsalsilveira.readr.service.command.CommandResponse;
import br.com.marsalsilveira.readr.utils.Strings;

import java.util.concurrent.TimeUnit;

/**
 * Main app controller responsible for manage all app flux and rules.
 */
final class Readr {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final ReadrConsole _console;
    private final ReadrService _service = ServicePool.shared.getService();

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    Readr(ReadrConsole console) {

        _console = console;
    }

    //******************************************************************************************************************
    //* Run
    //******************************************************************************************************************

    void run() {

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
                        response.messages().forEach(_console::print);
                    } catch (InvalidCommandException e) {

                        _console.printError(e.getLocalizedMessage());
                    }
                }

                // TODO: [Workaround] I don't known why but sometimes `_console` messes up all messages... so we put this sleep to synchronize it
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!input.equals(Strings.exit));

        } finally {

            _console.print(Strings.finishing);
            _console.close();
        }
    }

    //******************************************************************************************************************
    //* Output Text
    //******************************************************************************************************************

    private void welcome() {

        // Header
        _console.print(Strings.welcome);
        _console.print();
        // TODO: hardcoded... check a way to get this info from maven pom file...
        _console.print(String.format(Strings.version, "1.0-SNAPSHOT"));
        _console.print();
        _console.print(Strings.author);

        // Available File Fields
        _console.print();
        String fileFields = _service.fields().stream().reduce("", (v1, v2) -> v1 + (v1.equals("") ? "" : "\n" + Strings.tab) + v2);
        _console.print(String.format(Strings.fileFields, fileFields));
    }

    private void commands() {

        _console.print();
        String commands = _service.commands().stream().reduce("", (v1, v2) -> v1 + (v1.equals("") ? "" : "\n" + Strings.tab) + v2);
        _console.print(String.format(Strings.commands, commands));
    }
}