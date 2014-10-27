package org.pongmatcher.repositories;

import org.pongmatcher.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, String> {

    Match findByMatchRequest1IdOrMatchRequest2Id(String matchRequest1Id, String matchRequest2Id);

}
