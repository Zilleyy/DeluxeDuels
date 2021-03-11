package com.prophaze.luxduels.util;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.Base64;

/**
 * Author: Zilleyy
 * <br>
 * Date: 11/03/2021 @ 5:17 pm AEST
 */
public class Serialize {

    public static String encodeObject(final Object object) {
        final String encodedObject;
        try {
            final ByteArrayOutputStream io = new ByteArrayOutputStream();
            final ObjectOutputStream os = new ObjectOutputStream(io);

            os.writeObject(object);
            os.flush();

            final byte[] serializedObject = io.toByteArray();

            encodedObject = Base64.getEncoder().encodeToString(serializedObject);

            return encodedObject;
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String encodeBukkitObject(final Object object) {
        final String encodedObject;
        try {
            final ByteArrayOutputStream io = new ByteArrayOutputStream();
            final BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

            os.writeObject(object);
            os.flush();

            final byte[] serializedObject = io.toByteArray();

            encodedObject = Base64.getEncoder().encodeToString(serializedObject);

            return encodedObject;
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] serializeToByteArray(final Object object) {
        return null;
    }

    public static <T> T deserializeEncodedObject(final String encodedObject, final Class<T> cast) {
        final byte[] serializedObject = decodeToByteArray(encodedObject);

        try {
            final ByteArrayInputStream in = new ByteArrayInputStream(serializedObject);
            final ObjectInputStream is = new ObjectInputStream(in);

            return (T) is.readObject();
        } catch (final IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object deserializeEncodedObject(final String encodedObject) {
        final byte[] serializedObject = decodeToByteArray(encodedObject);

        try {
            final ByteArrayInputStream in = new ByteArrayInputStream(serializedObject);
            final ObjectInputStream is = new ObjectInputStream(in);

            return is.readObject();
        } catch (final IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static <T> T deserializeEncodedBukkitObject(final String encodedObject, final Class<T> cast) {
        final byte[] serializedObject = decodeToByteArray(encodedObject);

        try {
            final ByteArrayInputStream in = new ByteArrayInputStream(serializedObject);
            final BukkitObjectInputStream is = new BukkitObjectInputStream(in);

            return (T) is.readObject();
        } catch (final IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object deserializeEncodedBukkitObject(final String encodedObject) {
        return deserializeEncodedBukkitObject(encodedObject, Object.class);
    }

    public static byte[] decodeToByteArray(final String encodedObject) {
        return Base64.getDecoder().decode(encodedObject);
    }

}
