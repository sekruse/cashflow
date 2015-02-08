package org.github.sekruse.cashflow.util;

import junit.framework.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class SQLiteUtilsTest {

    @Test
    public void testToBytes() throws Exception {
        UUID uuid = UUID.randomUUID();
        byte[] bytes = SQLiteUtils.toBytes(uuid);
        UUID copy = SQLiteUtils.toUUID(bytes);

        assertEquals(uuid, copy);
        System.out.println(uuid);
        System.out.println(copy);
    }
}