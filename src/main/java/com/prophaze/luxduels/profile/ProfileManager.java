package com.prophaze.luxduels.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: Zilleyy, ProPhaze
 * <br>
 * Date: 9/03/2021 @ 9:57 am AEST
 */
public class ProfileManager {

    private static List<Profile> profiles = new ArrayList<>();

    // Optional<?>#findAny()#get() might throw a NullPointerException if it didn't find anything I'm not sure...
    public static Profile getProfileByUUID(UUID uuid) {
        return profiles.stream().filter(profile -> profile.getUUID().equals(uuid)).findAny().get();
    }

    public static void loadProfile(UUID uuid) {
        Profile profile = new Profile(uuid);
        if(profile.getFile().get("players." + uuid) != null) {
            profile.setPlayerStats(Statistics.fromString(profile.getFile().getString("players." + uuid + ".stats")));
        } else profile.setPlayerStats(new Statistics(profile));
        profiles.add(profile);

    }

    private static void addProfile(Profile profile) {
        profiles.add(profile);
    }

    private static void removeProfile(Profile profile) {
        profiles.remove(profile);
    }

}
