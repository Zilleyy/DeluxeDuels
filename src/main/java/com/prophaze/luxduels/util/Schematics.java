package com.prophaze.luxduels.util;

import com.prophaze.luxduels.LuxDuels;

import java.io.File;
import java.util.Objects;

public class Schematics {

    private static final File[] schematics = new File(LuxDuels.getInstance().getDataFolder() + File.separator + "Schematics").listFiles();

    public static File getSchematic(String fileName) {
        for (File file : Objects.requireNonNull(schematics)) {
            if (file.getName().equalsIgnoreCase(fileName)) return file;
        }
        return null;
    }

    public static String[] getSchematicNames() {
        String[] schemNames = new String[Objects.requireNonNull(schematics).length];
        for(int i = 0; i < schemNames.length; i++) schemNames[i] = schematics[i].getName();

        return schemNames;
    }

    public static String getRandomSchematic() {
        int index = new java.util.Random().nextInt(Schematics.getSchematicNames().length);
        return Schematics.getSchematicNames()[index];
    }

    public static boolean dirIsEmpty() {
        return schematics.length == 0;
    }

}
