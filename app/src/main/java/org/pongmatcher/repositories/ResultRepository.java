package org.pongmatcher.repositories;

import org.pongmatcher.domain.Result;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResultRepository extends CrudRepository<Result, String> {
    List<Result> findAllByWinnerIdOrLoserId(String winnerId, String loserId);
}
