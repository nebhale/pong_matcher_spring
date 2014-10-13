package org.pongmatcher;

public class MatchRequest {
  private final String id;
  private final String requesterId;

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
