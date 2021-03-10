package com.prophaze.luxduels.match;

public enum MatchType {

    CASUAL,
    COMP,
    SHIELD,
    NETHERITE,
    DIAMOND,
    OVERPOWERED,
    POTION,
    UHC,
    CUSTOM;

    public static String[] getStringValues() {
        String[] values = new String[MatchType.values().length];
        for(int i = 0; i < MatchType.values().length; i++) {
            values[i] = MatchType.values()[i].name();
        }
        return values;
    }

}
