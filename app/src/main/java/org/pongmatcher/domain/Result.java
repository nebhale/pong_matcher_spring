package org.pongmatcher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public final class Result {
    @Id
    private volatile String id;

    private volatile String winnerId;

    private volatile String loserId;

    @JsonProperty("match_id")
    private volatile String matchId;

    Result() {
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
