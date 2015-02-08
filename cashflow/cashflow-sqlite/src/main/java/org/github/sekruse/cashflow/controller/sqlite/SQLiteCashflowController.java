package org.github.sekruse.cashflow.controller.sqlite;

import org.github.sekruse.cashflow.controller.*;
import org.github.sekruse.cashflow.model.Account;
import org.github.sekruse.cashflow.model.Share;
import org.github.sekruse.cashflow.model.User;
import org.github.sekruse.cashflow.model.sqlite.SQLiteUser;
import org.github.sekruse.cashflow.util.SQLiteUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Path;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;
import java.util.UUID;

/**
 * This is an implementation of a CashflowController dedicated to work with SQLite databases.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public class SQLiteCashflowController implements CashflowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLiteCashflowController.class);

    final Connection connection;

    SQLiteCashflowController(String dbUrl) throws CashflowControllerInstantiationException {
        try {
            Class.forName(org.sqlite.JDBC.class.getCanonicalName());
            this.connection = DriverManager.getConnection(dbUrl);
            this.connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new CashflowControllerInstantiationException(e);
        } catch (SQLException e) {
            throw new CashflowControllerInstantiationException(e);
        }
    }

    @Override
    public User createUser(String name) throws CashflowWriteException {
        // Create a new user object.
        SQLiteUser user = SQLiteUser.createNewUser(name);

        // Write the object into database.
        String sql = "INSERT INTO User (name, uuid) VALUES (?, ?);";
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setBytes(2, SQLiteUtils.toBytes(user.getId()));
            statement.execute();

            // Get the auto-generated keys.
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException("Could not fetch auto-generated key.");
                }
                user.setDbId(generatedKeys.getInt(1));
            }
            this.connection.commit();

        } catch (SQLException e) {
            // Rollback
            try {
                this.connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("Rollback of user " + user + " failed.", e1);
            }

            throw new CashflowWriteException(e);
        }

        return user;
    }

    @Override
    public Collection<User> getAllUsers() throws CashflowReadException {
        Collection<User> users = new LinkedList<>();

        // Read all users from the DB.
        try (Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT id, name, uuid FROM User;");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                byte[] uuidBytes = resultSet.getBytes(3);
                UUID uuid = SQLiteUtils.toUUID(uuidBytes);

                // Add the user to the collection.
                users.add(new SQLiteUser(name, uuid, id));
            }

        } catch (SQLException e) {
            throw new CashflowReadException("Could not read users.", e);
        }
        return users;
    }

    @Override
    public Account createAccount(String name, User user) {
    // todo
        return null;
    }

    @Override
    public Share createShare(String name, Account account, Collection<User> users) {
        // TODO
        return null;
    }

    @Override
    public TransactionBuilder buildTransaction() {
        return null;
    }

    /**
     * Creates the database schema.
     */
    void createSchema() throws IOException, SQLException {
        // Read the SQL script.
        StringBuilder sb = new StringBuilder();
        try (InputStream in = getClass().getResourceAsStream("/initialize-schema.sql")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            // Execute the script.
            SQLiteUtils.runScript(reader, this.connection);
            this.connection.commit();
        }
    }

    @Override
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
           LOGGER.error("Closing failed.", e);
        }
    }
}
