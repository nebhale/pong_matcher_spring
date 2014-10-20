package org.pongmatcher;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MatchRequest {
    @Id
    private final String id;
    private final String requesterId;

    protected MatchRequest() {
        this.id = null;
        this.requesterId = null;
    }

    public MatchRequest(String id, String requesterId) {
        this.id = id;
        this.requesterId = requesterId;
    }

    public String getId() {
        return id;
    }

    public String getRequesterId() {
        return requesterId;
    }
}
