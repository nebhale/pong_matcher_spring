package org.pongmatcher.repositories;

import org.pongmatcher.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, String> {
    List<Result> findAllByWinnerIdOrLoserId(String winnerId, String loserId);
}
