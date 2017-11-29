package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.console.Console;
import br.com.marsalsilveira.readr.exception.InvalidFileException;
import br.com.marsalsilveira.readr.service.Service;
import br.com.marsalsilveira.readr.service.ServicePool;

import java.io.FileNotFoundException;

/**
 *
 */
public final class App {

    public static void main( String[] args ) {

        // hardcoded... this can be replaced by another way to get the file path like a user selection sent by param...
        String filePath = "src/main/resources/cidades.csv";

        // Configure Service Layer...
        try {

            ServicePool.shared.setup(new Service(filePath));
        } catch (FileNotFoundException | InvalidFileException e) {

            // if file not found or is invalid print exception and exit ...
            System.err.println("Could not find file: " + filePath);
            e.printStackTrace();
            System.exit(1);
        }

        // Configure Readr (App Coordinator) and Std Console and start program exection ...
        new Readr(new Console()).run();
    }
}