package org.github.sekruse.cashflow.model.sqlite;

import org.github.sekruse.cashflow.model.User;

import java.util.UUID;

/**
 * @author Sebastian
 * @since 08.02.2015.
 */
public class SQLiteUser implements User {

    private final String name;

    private final UUID uuid;

    private int id;

    public static SQLiteUser createNewUser(String name) {
        return new SQLiteUser(name, UUID.randomUUID());
    }

    public SQLiteUser(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public SQLiteUser(String name, UUID uuid, int id) {
        this(name, uuid);
        this.id = id;
    }

    @Override
    public UUID getId() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setDbId(int id) {
        this.id = id;
    }

    public int getDbId() { return this.id; }

    @Override
    public String toString() {
        return "SQLiteUser{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SQLiteUser that = (SQLiteUser) o;

        if (!name.equals(that.name)) return false;
        if (!uuid.equals(that.uuid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
