package br.com.marsalsilveira.readr.application;

import br.com.marsalsilveira.readr.application.console.Console;
import br.com.marsalsilveira.readr.service.Service;
import br.com.marsalsilveira.readr.service.ServicePool;
import br.com.marsalsilveira.readr.exception.InvalidFileException;

import java.io.FileNotFoundException;

/**
 *
 */
public final class App {

    public static void main( String[] args ) {

        // Configure Service Layer...
        String filePath = "src/main/resources/cidades.csv";
        try {

            ServicePool.shared.setup(new Service(filePath));
        } catch (FileNotFoundException | InvalidFileException e) {

            System.err.println("Could not find file: " + filePath);
            e.printStackTrace();
            System.exit(1);
        }

        // Configure Readr (App Coordinator) and Std Console... so start program execution.
        new Readr(new Console()).run();
    }
}