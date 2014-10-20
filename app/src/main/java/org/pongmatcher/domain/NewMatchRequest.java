package org.pongmatcher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewMatchRequest {

    @JsonProperty("player")
    private final String requesterId;

    protected NewMatchRequest() {
        this.requesterId = null;
    }

    public String getRequesterId() {
        return requesterId;
    }
}

