package br.com.marsalsilveira.readr.application.contracts;

/**
 *
 */
public interface ReadrTerminal {

    void print(String text);
    void print();

    String input(String prefix);
    String input();
}
