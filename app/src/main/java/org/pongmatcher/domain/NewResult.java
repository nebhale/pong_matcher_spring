package org.pongmatcher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class NewResult {
    @JsonProperty("winner")
    private volatile String winnerId;

    @JsonProperty("loser")
    private volatile String loserId;

    @JsonProperty("match_id")
    private volatile String matchId;

    NewResult() {
    }

    public NewResult(String winnerId, String loserId, String matchId) {
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

    public String getMatchId() {
        return matchId;
    }
}
