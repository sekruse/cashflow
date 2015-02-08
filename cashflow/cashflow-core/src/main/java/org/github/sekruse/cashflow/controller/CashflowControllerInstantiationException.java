package org.github.sekruse.cashflow.controller;

/**
 * This exception tells that a CashflowController could not be instantiated.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public class CashflowControllerInstantiationException extends CashflowException {

    public CashflowControllerInstantiationException() {
        super();
    }

    public CashflowControllerInstantiationException(String message) {
        super(message);
    }

    public CashflowControllerInstantiationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CashflowControllerInstantiationException(Throwable cause) {
        super(cause);
    }
}
