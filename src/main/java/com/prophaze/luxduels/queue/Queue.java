package com.prophaze.luxduels.queue;

import com.prophaze.luxduels.match.MatchType;
import com.prophaze.luxduels.profile.Profile;

import java.util.HashMap;
import java.util.LinkedList;

public class Queue {

    private final HashMap<MatchType, LinkedList<Profile>> queue = new HashMap<>();

    public void addProfile(MatchType matchType, Profile profile) {
        if(this.queue.containsKey(matchType)) {
            this.queue.get(matchType).add(profile);
        } else {
            LinkedList<Profile> list = new LinkedList<>();
            list.add(profile);
            this.queue.put(matchType, list);
        }
    }

    public void removeProfile(Profile profile) {
        for(MatchType matchType : this.queue.keySet()) {
            for(Profile queued : this.queue.get(matchType)) {
                if(queued.equals(profile)) {
                    this.queue.get(matchType).remove(queued);
                    return;
                }
            }
        }
    }

}
