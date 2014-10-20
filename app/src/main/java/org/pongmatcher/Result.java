package org.pongmatcher;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Result {
    @Id
    private final String id;

    private final String winnerId;
    private final String loserId;

    @JsonProperty("match_id")
    private final String matchId;

    protected Result() {
        this.id = null;
        this.winnerId = null;
        this.loserId = null;
        this.matchId = null;
    }

    public Result(String id, String winnerId, String loserId, String matchId) {
        this.id = id;
        this.winnerId = winnerId;
        this.loserId = loserId;
        this.matchId = matchId;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public String getLoserId() {
        return loserId;
    }
}