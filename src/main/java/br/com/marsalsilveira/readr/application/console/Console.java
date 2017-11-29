package br.com.marsalsilveira.readr.application.console;

import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.Scanner;

/**
 * Abstraction to std console (cli).
 */
public final class Console implements ReadrConsole {

    //******************************************************************************************************************
    //* Properties
    //******************************************************************************************************************

    private final Scanner _scanner;

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public Console() {

        _scanner = new Scanner(System.in);
    }

    //******************************************************************************************************************
    //* Open / Close
    //******************************************************************************************************************

    public void close() {

        _scanner.close();
    }

    //******************************************************************************************************************
    //* Output / Input
    //******************************************************************************************************************

    public void print() {

        this.print(null);
    }

    public void print(String str) {

        // to avoid print `null` into console
        System.out.println(StringUtils.isEmpty(str) ? "" : str);
    }

    public void printError(String str) {

        // to avoid print `null` into console
        System.err.println(StringUtils.isEmpty(str) ? "" : str);
    }

    public String input(String prefix) {

        System.out.print(StringUtils.isEmpty(prefix ) ? "> " : prefix);

        // wait until user type any value...
        String input = _scanner.nextLine();

        return input.trim().toLowerCase();
    }

    public String input() {

        return this.input(null);
    }
}