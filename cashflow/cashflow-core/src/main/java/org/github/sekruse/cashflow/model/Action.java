package org.github.sekruse.cashflow.model;

import java.math.BigDecimal;

/**
 * @author Sebastian
 * @since 03.02.2015.
 */
public interface Action {

    /**
     *
     * @return the register on which this action operates
     */
    Register getRegister();

    /**
     *
     * @return the added (potentially negative) amount of added money
     */
    BigDecimal getAmount();

    /**
     *
     * @return the transaction that comprises this action
     */
    Transaction getTransaction();

}
