package br.com.marsalsilveira.readr.service.command;

import java.io.IOException;

/**
 *
 */
public class InvalidInputException extends IOException {

    /**
     * Constructs a <code>InvalidInputException</code> with
     * <code>null</code> as its error detail message.
     */
    public InvalidInputException() {

        super();
    }

    /**
     * Constructs a <code>InvalidInputException</code> with the
     * specified detail message. The string <code>s</code> can be
     * retrieved later by the
     * <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */
    public InvalidInputException(String s) {

        super(s);
    }
}