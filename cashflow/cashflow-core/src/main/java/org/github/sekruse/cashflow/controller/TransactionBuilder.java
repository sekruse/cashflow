package org.github.sekruse.cashflow.controller;

import org.github.sekruse.cashflow.model.Register;
import org.github.sekruse.cashflow.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sebastian
 * @since 03.02.2015.
 */
public interface TransactionBuilder {

    TransactionBuilder setName(String name);

    TransactionBuilder setCategory(String name);

    TransactionBuilder setDate(Date date);

    TransactionBuilder addAction(Register register, BigDecimal amount);

    Transaction build();

}
