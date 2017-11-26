package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.contracts.ReadrTerminal;
import br.com.marsalsilveira.readr.utils.Strings;

/**
 * Main app controller responsible for manage all app flux and rules.
 */
public class Readr {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final ReadrTerminal _terminal;

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public Readr(ReadrTerminal terminal) {

        _terminal = terminal;
    }

    //******************************************************************************************************************
    //* Init
    //******************************************************************************************************************

    public void init() {
        this.welcome();
        this.statements();
    }

    //******************************************************************************************************************
    //* Header
    //******************************************************************************************************************

    private void welcome() {

        _terminal.print(Strings.welcome);
        _terminal.print(Strings.description);
        _terminal.print();
        _terminal.print(Strings.version);
        // TODO: hardcoded... check a way to get this info from maven pom file...
        _terminal.print("1.0-SNAPSHOT");
    }

    private void statements() {

        _terminal.print();
        _terminal.print(Strings.commands);
    }
}