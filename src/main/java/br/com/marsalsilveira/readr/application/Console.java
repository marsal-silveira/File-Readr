package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.contracts.ReadrConsole;
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

    @Override
    public void print(String str) {

        // to avoid print `null` into console
        System.out.println(StringUtils.isEmpty(str) ? "" : str);
    }

    @Override
    public void print() {

        this.print(null);
    }

    @Override
    public String input(String prefix) {

        System.out.print(StringUtils.isEmpty(prefix ) ? "> " : prefix);

        // wait until user type any value...
        String input = _scanner.nextLine();

        return input.trim().toLowerCase();
    }

    @Override
    public String input() {

        return this.input(null);
    }
}