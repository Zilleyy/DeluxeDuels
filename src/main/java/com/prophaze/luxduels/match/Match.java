package com.prophaze.luxduels.match;

import com.prophaze.luxduels.profile.Profile;

/**
 * Author: Zilleyy
 * <br>
 * Date: 9/03/2021 @ 10:42 am AEST
 */
public class Match {

    private Profile one, two;

    public Match(Profile one, Profile two) {
        this.one = one;
        this.two = two;
    }

    public Profile getProfileOne() {
        return this.one;
    }

    public Profile getProfileTwo() {
        return this.two;
    }

}
