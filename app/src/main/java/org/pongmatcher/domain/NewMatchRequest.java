package org.pongmatcher.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class NewMatchRequest {

    @JsonProperty("player")
    private volatile String requesterId;

    NewMatchRequest() {
    }

    public String getRequesterId() {
        return requesterId;
    }
}

