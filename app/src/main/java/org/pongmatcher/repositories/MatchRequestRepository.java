package org.pongmatcher.repositories;

import org.pongmatcher.domain.MatchRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRequestRepository extends CrudRepository<MatchRequest, String> {
    List<MatchRequest> findAll(Sort sort);
}
