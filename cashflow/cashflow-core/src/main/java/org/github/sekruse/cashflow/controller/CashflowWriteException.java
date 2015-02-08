package org.github.sekruse.cashflow.controller;

/**
 * This exception signals an error when writing something.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public class CashflowWriteException extends CashflowException {

    public CashflowWriteException() {
        super();
    }

    public CashflowWriteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CashflowWriteException(String message) {
        super(message);
    }

    public CashflowWriteException(Throwable cause) {
        super(cause);
    }
}
