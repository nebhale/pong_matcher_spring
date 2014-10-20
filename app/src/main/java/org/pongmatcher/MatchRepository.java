package org.pongmatcher;

import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, String> {
    Match findByMatchRequest1IdOrMatchRequest2Id(String matchRequest1Id, String matchRequest2Id);
}
