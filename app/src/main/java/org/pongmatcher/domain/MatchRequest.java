package org.pongmatcher.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class MatchRequest {

    @GeneratedValue
    @Id
    private volatile Long id;

    private volatile String uuid;

    private volatile String requesterId;

    MatchRequest() {
    }

    public MatchRequest(String uuid, String requesterId) {
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
