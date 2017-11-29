package br.com.marsalsilveira.readr.exception;

import java.io.IOException;

/**
 *
 */
public class InvalidFileException extends IOException {

    /**
     * Constructs a <code>InvalidFileException</code> with the
     * specified detail message. The string <code>s</code> can be
     * retrieved later by the
     * <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */
    public InvalidFileException(String s) {

        super(s);
    }
}