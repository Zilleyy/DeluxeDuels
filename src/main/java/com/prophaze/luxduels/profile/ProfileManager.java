package com.prophaze.luxduels.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 9:57 am AEST
 */
public class ProfileManager {

    private List<Profile> profiles = new ArrayList<>();

    public Profile getProfileByUUID(UUID uuid) {
        return null;
    }

    public void addProfile(UUID uuid) {
        this.profiles.add(new Profile(uuid));
    }

    public void removeProfile(Profile profile) {
        this.profiles.remove(profile);
    }

}
