package org.github.sekruse.cashflow.model;

/**
 * A share is the stake of a stakeholder wrt. a real account.
 *
 * @author Sebastian
 * @since 03.02.2015.
 */
public interface Share extends Register {

    /**
     *
     * @return the account that backs this share
     */
    Account getAccount();

}
