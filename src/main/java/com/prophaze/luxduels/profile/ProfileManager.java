package com.prophaze.luxduels.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 9:57 am AEST
 */
public class ProfileManager {

    private List<Profile> profiles = new ArrayList<>();

    // Optional<?>#findAny()#get() might throw a NullPointerException if it didn't find anything I'm not sure...
    public Profile getProfileByUUID(UUID uuid) {
        return this.profiles.stream().filter(profile -> profile.getUUID().equals(uuid)).findAny().get();
    }

    // TODO LOAD PROFILE FROM FILE.
    public void loadProfile(UUID uuid) {
        Profile profile = new Profile(uuid);
        addProfile(profile);
    }

    private void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    private void removeProfile(Profile profile) {
        this.profiles.remove(profile);
    }

}
