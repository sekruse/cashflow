package org.github.sekruse.cashflow.controller.sqlite;

import org.github.sekruse.cashflow.controller.CashflowControllerFactory;
import org.github.sekruse.cashflow.controller.CashflowControllerInstantiationException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This factory creates SQLiteCashflowController instances.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public class SQLiteCashflowControllerFactory implements CashflowControllerFactory<SQLiteCashflowController> {

    public static final String DB_URL = "db.url";

    @Override
    public SQLiteCashflowController create(Properties properties) throws CashflowControllerInstantiationException {
        SQLiteCashflowController controller = createInstance(properties);
        try {
            controller.createSchema();
        } catch (IOException | SQLException e) {
            throw new CashflowControllerInstantiationException("Could not create the database schema.", e);
        }
        return controller;
    }

    @Override
    public SQLiteCashflowController connect(Properties properties) throws CashflowControllerInstantiationException {
        return createInstance(properties);
    }

    private SQLiteCashflowController createInstance(Properties properties) throws CashflowControllerInstantiationException {
        String dbUrl = properties.getProperty(DB_URL);
        return new SQLiteCashflowController(dbUrl);
    }
}
