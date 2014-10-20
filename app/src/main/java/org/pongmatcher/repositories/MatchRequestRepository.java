package org.pongmatcher.repositories;

import org.pongmatcher.domain.MatchRequest;
import org.springframework.data.repository.CrudRepository;

public interface MatchRequestRepository extends CrudRepository<MatchRequest, String> {
}
