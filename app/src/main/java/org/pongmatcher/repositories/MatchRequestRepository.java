package org.pongmatcher.repositories;

import org.pongmatcher.domain.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRequestRepository extends JpaRepository<MatchRequest, String> {

    List<MatchRequest> findByOrderByIdAsc();

}
