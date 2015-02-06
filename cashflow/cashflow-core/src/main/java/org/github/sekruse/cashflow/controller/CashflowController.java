package org.github.sekruse.cashflow.controller;

import org.github.sekruse.cashflow.model.Account;
import org.github.sekruse.cashflow.model.User;

import java.util.Collection;

/**
 * @author Sebastian
 * @since 03.02.2015.
 */
public interface CashflowController {

    void createUser(String name);

    void createAccount(String name, User user);

    void createShare(String name, Account account, Collection<User> users);

    TransactionBuilder buildTransaction();

}
