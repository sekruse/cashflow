package org.github.sekruse.cashflow.controller.sqlite;

import org.github.sekruse.cashflow.controller.CashflowControllerInstantiationException;
import org.github.sekruse.cashflow.controller.CashflowException;
import org.github.sekruse.cashflow.model.User;
import org.github.sekruse.cashflow.model.sqlite.SQLiteUser;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class SQLiteCashflowControllerTest {

    @Test
    public void testCreateSchema() throws CashflowControllerInstantiationException, IOException, SQLException {
        File file = File.createTempFile("test", "db");
        file.deleteOnExit();
        SQLiteCashflowController controller = new SQLiteCashflowController("jdbc:sqlite:" + file.toURI().getPath());
        controller.createSchema();
        controller.close();
    }

    @Test
    public void testStoringAndReadingUsers() throws CashflowException, IOException, SQLException {
        File file = File.createTempFile("test", "db");
        file.deleteOnExit();
        String dbUrl = "jdbc:sqlite:" + file.toURI().getPath();

        // Create the users.
        Collection<User> users = new LinkedList<>();
        SQLiteCashflowController controller = new SQLiteCashflowController(dbUrl);
        controller.createSchema();
        users.add(controller.createUser("John Doe"));
        users.add(controller.createUser("Joe Dohn"));
        controller.close();

        // Load the users with a new controller.
        controller = new SQLiteCashflowController(dbUrl);
        Collection<User> loadedUsers = controller.getAllUsers();
        controller.close();

        // Compare the original and loaded users.
        assertTrue(users.containsAll(loadedUsers));
        assertTrue(loadedUsers.containsAll(users));

        LoggerFactory.getLogger("test").debug(loadedUsers.toString());
    }

}