package com.prophaze.luxduels.queue;

import com.prophaze.luxduels.arena.ArenaManager;
import com.prophaze.luxduels.match.Match;
import com.prophaze.luxduels.match.MatchManager;
import com.prophaze.luxduels.match.MatchType;
import com.prophaze.luxduels.profile.Profile;

import java.util.HashMap;
import java.util.LinkedList;

public class Queue {

    private static HashMap<MatchType, LinkedList<Profile>> queue = new HashMap<>();

    public static void addProfile(MatchType matchType, Profile profile) {
        if(queue.containsKey(matchType)) {
            queue.get(matchType).add(profile);
        } else {
            LinkedList list = new LinkedList<Profile>();
            list.add(profile);
            queue.put(matchType, list);
        }
    }

    public static void removeProfile(Profile profile) {
        for(MatchType matchType : queue.keySet()) {
            for(Profile queued : queue.get(matchType)) {
                if(queued.equals(profile)) {
                    queue.get(matchType).remove(queued);
                    return;
                }
            }
        }
    }

    public static boolean isInQueue(Profile profile) {
        return queue.containsValue(profile);
    }

    public MatchType getTypeOf(Profile profile) {
        if(!isInQueue(profile)) return null;
        for (MatchType type : queue.keySet()) {
            if(queue.containsValue(profile)) return type;
        }
        return null;
    }

    public static int getSize(MatchType type) {
        return queue.get(type).size();
    }

    public static int getPositionOf(Profile profile) {
        if(!isInQueue(profile)) return -1;
        for(MatchType type : queue.keySet()) {
            for(Profile queued : queue.get(type)) {
                if(queued.equals(profile)) return queue.get(type).indexOf(profile);
            }
        }
        return -1;
    }

    /**
     * Use instead of null checking next(MatchType).
     * @param matchType
     * @return
     */
    public static boolean hasNext(MatchType matchType) {
        if(queue.get(matchType) == null) return false;
        if(queue.get(matchType).size() < 2) return false;
        return true;
    }

    public static Match next(MatchType matchType) {
        if(queue.get(matchType) == null) return null;
        if(queue.get(matchType).size() < 2) return null;
        // Will need to add a thing to check if there is no vacant arenas, if there isn't -> create a new arena. (or wait)
        Profile p1, p2;
        p1 = queue.get(matchType).get(0);
        p2 = queue.get(matchType).get(1);
        queue.get(matchType).remove(p1);
        queue.get(matchType).remove(p2);
        return MatchManager.createAndGet(ArenaManager.getVacant(), matchType, p1, p2);
    }

}
