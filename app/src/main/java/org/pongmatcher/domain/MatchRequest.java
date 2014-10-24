package org.pongmatcher.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MatchRequest {
    @Id @GeneratedValue
    private final Long id;

    private final String uuid;
    private final String requesterId;

    protected MatchRequest() {
        this.id = null;
        this.uuid = null;
        this.requesterId = null;
    }

    public MatchRequest(String uuid, String requesterId) {
        this.id = null;
        this.uuid = uuid;
        this.requesterId = requesterId;
    }

    public String getUuid() {
        return uuid;
    }

    public String getRequesterId() {
        return requesterId;
    }
}
