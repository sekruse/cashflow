package org.github.sekruse.cashflow.model;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * A transaction comprises a logical group of {@link Action}s.
 *
 * @author Sebastian
 * @since 03.02.2015.
 */
public interface Transaction {

    /**
     *
     * @return the actions comprised by this transaction
     */
    Collection<Action> getActions();

    Date getDate();

    String getCategory();

    String getDescription();

    UUID getId();

}
