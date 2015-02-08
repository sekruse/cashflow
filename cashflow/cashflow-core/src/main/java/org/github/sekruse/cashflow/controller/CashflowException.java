package org.github.sekruse.cashflow.controller;

/**
 * @author Sebastian
 * @since 08.02.2015.
 */
public abstract class CashflowException extends Exception {

    public CashflowException() {
        super();
    }

    public CashflowException(String message) {
        super(message);
    }

    public CashflowException(String message, Throwable cause) {
        super(message, cause);
    }

    public CashflowException(Throwable cause) {
        super(cause);
    }
}
