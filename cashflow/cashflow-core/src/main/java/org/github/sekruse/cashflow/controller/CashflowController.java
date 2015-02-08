package org.github.sekruse.cashflow.controller;

import org.github.sekruse.cashflow.model.Account;
import org.github.sekruse.cashflow.model.Share;
import org.github.sekruse.cashflow.model.User;

import java.util.Collection;
import java.util.Properties;

/**
 * @author Sebastian
 * @since 03.02.2015.
 */
public interface CashflowController {

    User createUser(String name);

    Account createAccount(String name, User user);

    Share createShare(String name, Account account, Collection<User> users);

    TransactionBuilder buildTransaction();

}
