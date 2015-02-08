package org.github.sekruse.cashflow.controller;

/**
 * This exception signals an error when reading something.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public class CashflowReadException extends CashflowException {

    public CashflowReadException() {
        super();
    }

    public CashflowReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public CashflowReadException(String message) {
        super(message);
    }

    public CashflowReadException(Throwable cause) {
        super(cause);
    }
}
