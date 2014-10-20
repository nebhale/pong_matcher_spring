package org.pongmatcher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewResult {
    @JsonProperty("winner")
    private final String winnerId;

    @JsonProperty("loser")
    private final String loserId;

    @JsonProperty("match_id")
    private final String matchId;

    protected NewResult() {
        this.winnerId = null;
        this.loserId = null;
        this.matchId = null;
    }

    public String getWinnerId() {
        return winnerId;
    }

    public String getLoserId() {
        return loserId;
    }

    public String getMatchId() {
        return matchId;
    }
}
