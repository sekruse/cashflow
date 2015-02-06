package org.github.sekruse.cashflow.model;

import java.util.Collection;
import java.util.UUID;

/**
 * A register is any entity that holds money of its stakeholders.
 * 
 * @author Sebastian Kruse
 * @since 03.02.2015.
 */
public interface Register {

    /**
     *
     * @return the IDs of the stakeholders
     */
    Collection<UUID> getStakeholderIds();

    /**
     *
     * @return whether this register really holds accessible money
     */
    boolean isSolvent();

}
