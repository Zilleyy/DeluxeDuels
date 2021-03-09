package com.prophaze.luxduels.queue;

import com.prophaze.luxduels.match.MatchType;
import com.prophaze.luxduels.profile.Profile;

import java.util.HashMap;
import java.util.LinkedList;

public class Queue {

    private HashMap<MatchType, LinkedList<Profile>> queue = new HashMap<>();

    public static void addProfile(MatchType matchType, Profile profile) {
        
    }

}
