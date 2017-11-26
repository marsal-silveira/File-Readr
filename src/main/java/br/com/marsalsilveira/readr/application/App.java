package br.com.marsalsilveira.readr.application;

/**
 *
 */
public class App {

    public static void main( String[] args ) {

        // just build app and start it execution...

        // Std Terminal (View)
        Terminal terminal = new Terminal();

        // App Coordinator
        Readr readr = new Readr(terminal);
        readr.init();
    }
}