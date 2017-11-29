package br.com.marsalsilveira.readr.exception;

import br.com.marsalsilveira.readr.utils.Strings;

import java.io.IOException;

/**
 *
 */
public class InvalidCommandException extends IOException {

    /**
     * Constructs a <code>InvalidCommandException</code> with
     * <code>null</code> as its error detail message.
     */
    public InvalidCommandException() {

        super(Strings.invalidCommandExceptionDefaultMessage);
    }

    /**
     * Constructs a <code>InvalidCommandException</code> with the
     * specified detail message. The string <code>s</code> can be
     * retrieved later by the
     * <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */
    public InvalidCommandException(String s) {

        super(s);
    }
}