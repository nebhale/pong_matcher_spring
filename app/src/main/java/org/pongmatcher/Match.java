package org.pongmatcher;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Match {
    @Id
    private final String id;

    private final String matchRequest1Id;
    private final String matchRequest2Id;

    protected Match() {
        this.id = null;
        this.matchRequest1Id = null;
        this.matchRequest2Id = null;
    }

    public Match(String id, String matchRequest1Id, String matchRequest2Id) {
        this.id = id;
        this.matchRequest1Id = matchRequest1Id;
        this.matchRequest2Id = matchRequest2Id;
    }

    @Override
    public String toString() {
        return String.format("%s versus %s", id, matchRequest1Id, matchRequest2Id);
    }

    public String getId() {
        return id;
    }

    public String getMatchRequest1Id() {
        return matchRequest1Id;
    }

    public String getMatchRequest2Id() {
        return matchRequest2Id;
    }
}
