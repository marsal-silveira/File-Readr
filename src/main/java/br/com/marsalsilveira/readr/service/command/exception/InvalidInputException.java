package br.com.marsalsilveira.readr.service.command.exception;

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

    /**
     * Constructs a <code>InvalidInputException</code> with a detail message
     * consisting of the given pathname string followed by the given reason
     * string.  If the <code>reason</code> argument is <code>null</code> then
     * it will be omitted.  This private constructor is invoked only by native
     * I/O methods.
     */
    private InvalidInputException(String path, String reason) {

        super (path + ((reason == null)
                ? ""
                : " (" + reason + ")"));
    }
}