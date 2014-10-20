package org.pongmatcher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FulfilledMatchRequest extends MatchRequest {
    @JsonProperty("match_id")
    private String matchId;

    public FulfilledMatchRequest(String matchId) {
        this.matchId = matchId;
    }
}
