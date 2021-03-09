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

    private List<Profile> profiles = new ArrayList<>();

    // Optional<?>#findAny()#get() might throw a NullPointerException if it didn't find anything I'm not sure...
    public Profile getProfileByUUID(UUID uuid) {
        return this.profiles.stream().filter(profile -> profile.getUUID().equals(uuid)).findAny().get();
    }

    public void loadProfile(UUID uuid) {
        Profile profile = new Profile(uuid);
        if(profile.getFile().get("players." + uuid) != null) {
            profile.setPlayerStats(Statistics.fromString(profile.getFile().getString("players." + uuid + ".stats")));
        } else profile.setPlayerStats(new Statistics(profile));
        profiles.add(profile);

    }

    private void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    private void removeProfile(Profile profile) {
        this.profiles.remove(profile);
    }

}
