package org.github.sekruse.cashflow.controller;

import java.util.Properties;

/**
 * This factory helps to create a CashflowController.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public interface CashflowControllerFactory <T extends CashflowController> {

    /**
     * Create a cashflow controller with a new store.
     *
     * @param properties describe how the new store shall be created
     * @return a controller for the new store
     * @throws CashflowControllerInstantiationException
     */
    public abstract T create(Properties properties) throws CashflowControllerInstantiationException;

    /**
     * Creates a CashflowController for an existing store.
     * @param properties describe how to connect to the existing store
     * @return a controller that is connected to the store
     * @throws CashflowControllerInstantiationException
     */
    public abstract T connect(Properties properties) throws CashflowControllerInstantiationException;

}
