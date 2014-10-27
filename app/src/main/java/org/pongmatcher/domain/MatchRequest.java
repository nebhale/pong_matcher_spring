package org.pongmatcher.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MatchRequest {

    @GeneratedValue
    @Id
    private volatile Long id;

    private volatile String uuid;

    private volatile String requesterId;

    MatchRequest() {
    }

    public MatchRequest(String uuid, String requesterId) {
        this.id = null;
        this.uuid = uuid;
        this.requesterId = requesterId;
    }

    public final String getUuid() {
        return uuid;
    }

    public final String getRequesterId() {
        return requesterId;
    }

}
