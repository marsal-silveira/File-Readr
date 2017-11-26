package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.contracts.ReadrTerminal;
import br.com.marsalsilveira.readr.utils.StringUtils;

import java.util.Scanner;

/**
 * Abstraction to std console/terminal (cli).
 */
public final class Terminal implements ReadrTerminal {

    //******************************************************************************************************************
    //* Constructor
    //******************************************************************************************************************

    public Terminal() { }

    //******************************************************************************************************************
    //* Interface Implementation
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

        String input;
        Scanner scanner = new Scanner(System.in);
        try {

            // wait until user type any value...
            input = scanner.nextLine();
        } finally {

            scanner.close();
        }

        return input;
    }

    @Override
    public String input() {

        return this.input(null);
    }
}