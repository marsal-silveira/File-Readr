package br.com.marsalsilveira.readr.application.contracts;

/**
 *
 */
public interface ReadrConsole {

    //******************************************************************************************************************
    //* Open / Close
    //******************************************************************************************************************

    // default behavior for these methods are `do nothing...`.
    // Maybe these is unnecessary to some type of console.
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