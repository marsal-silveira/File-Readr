package br.com.marsalsilveira.readr.application.console;

/**
 *
 */
public interface ReadrConsole {

    //******************************************************************************************************************
    //* Open / Close
    //******************************************************************************************************************

    // default behavior for these methods are `do nothing...`.
    // Default behavior is `nothing` because these can be unnecessary to some kind of console.
    default void open() {}
    default void close() {}

    //******************************************************************************************************************
    //* Output / Input
    //******************************************************************************************************************

    void print(String text);
    void print();

    String input(String prefix);
    String input();
}