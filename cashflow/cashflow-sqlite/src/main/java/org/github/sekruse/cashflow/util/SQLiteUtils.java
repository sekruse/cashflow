package org.github.sekruse.cashflow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * This class offers utility methods for the SQLite database.
 *
 * @author Sebastian
 * @since 08.02.2015.
 */
public class SQLiteUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLiteUtils.class);

    private SQLiteUtils() {
    }

    public static byte[] toBytes(UUID uuid) {
        long longOne = uuid.getMostSignificantBits();
        long longTwo = uuid.getLeastSignificantBits();

        return new byte[]{
                (byte) (longOne >>> 56),
                (byte) (longOne >>> 48),
                (byte) (longOne >>> 40),
                (byte) (longOne >>> 32),
                (byte) (longOne >>> 24),
                (byte) (longOne >>> 16),
                (byte) (longOne >>> 8),
                (byte) longOne,
                (byte) (longTwo >>> 56),
                (byte) (longTwo >>> 48),
                (byte) (longTwo >>> 40),
                (byte) (longTwo >>> 32),
                (byte) (longTwo >>> 24),
                (byte) (longTwo >>> 16),
                (byte) (longTwo >>> 8),
                (byte) longTwo
        };
    }

    public static UUID toUUID(byte[] bytes) {
        long mostSigBits = ((bytes[0] & 0xFFL) << 56)
                | ((bytes[1] & 0xFFL) << 48)
                | ((bytes[2] & 0xFFL) << 40)
                | ((bytes[3] & 0xFFL) << 32)
                | ((bytes[4] & 0xFFL) << 24)
                | ((bytes[5] & 0xFFL) << 16)
                | ((bytes[6] & 0xFFL) << 8)
                | ((bytes[7] & 0xFFL));

        long leastSigBits = ((bytes[8] & 0xFFL) << 56)
                | ((bytes[9] & 0xFFL) << 48)
                | ((bytes[10] & 0xFFL) << 40)
                | ((bytes[11] & 0xFFL) << 32)
                | ((bytes[12] & 0xFFL) << 24)
                | ((bytes[13] & 0xFFL) << 16)
                | ((bytes[14] & 0xFFL) << 8)
                | ((bytes[15] & 0xFFL));

        return new UUID(mostSigBits, leastSigBits);
    }

    /**
     * A simple utility to run SQL scripts on a connection. These must adhere to these requirements:
     * <ol><li>comments must start at the beginning of a line</li>
     * <li>a statement-closing semicolon must be at the end of a line</li>
     * <li>it must not start transactions (would lead to nested transactions) or commit</li>
     * </ol>
     * Also note, that no commit will be performed (unless autocommit is true).
     *
     * @param reader provides the SQL script
     */
    public static void runScript(BufferedReader reader, Connection connection) throws IOException, SQLException {
        boolean isAutocommit = connection.getAutoCommit();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            // Create a buffer to concatenate lines of a statement.
            StringBuffer statementBuffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                // Skip comment lines.
                if (trimmedLine.startsWith("--")) {
                    continue;
                }

                // Add normal lines to the statement buffer.
                statementBuffer.append(line).append(" ");

                // Execute if statement is complete.
                if (trimmedLine.endsWith(";")) {
                    try {
                        statement.execute(statementBuffer.toString());
                    } catch (SQLException e) {
                        // Intercept exceptions to show problematic statement.
                        LOGGER.error("Error on statement: " + statementBuffer);
                        throw e;
                    }
                    statementBuffer.setLength(0);
                }
            }
            if (isAutocommit) {
                connection.commit();
            }
        } catch (SQLException e) {
            // Rollback
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("Rollback within script failed.", e1);
            }
            throw e;
        } finally {
            connection.setAutoCommit(isAutocommit);
        }
    }
}
