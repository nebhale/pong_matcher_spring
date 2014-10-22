package org.pongmatcher.domain;

import org.pongmatcher.repositories.MatchRepository;
import org.pongmatcher.repositories.MatchRequestRepository;
import org.pongmatcher.repositories.ResultRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Player {
    private String id;
    private MatchRepository matchRepository;
    private MatchRequestRepository matchRequestRepository;
    private ResultRepository resultRepository;

    public Player(String id,
                  MatchRepository matchRepository,
                  MatchRequestRepository matchRequestRepository,
                  ResultRepository resultRepository) {
        this.id = id;
        this.matchRepository = matchRepository;
        this.matchRequestRepository = matchRequestRepository;
        this.resultRepository = resultRepository;
    }

    public Optional<MatchRequest> firstOpenMatchRequest() {
        return unfulfilledMatchRequests()
                .filter(request -> !inappropriateOpponents().contains(request.getRequesterId()))
                .findFirst();
    }

    private Stream<MatchRequest> unfulfilledMatchRequests() {
        Iterable<MatchRequest> allRequests = matchRequestRepository.findAll();
        return StreamSupport.stream(allRequests.spliterator(), false)
                .filter(request -> notMatched(request.getId()));
    }

    private List<String> inappropriateOpponents() {
        List<String> list = previousOpponents();
        list.add(id);
        return list;
    }

    private List<String> previousOpponents() {
        return resultsInvolvingPlayer()
                .map(result -> result.getWinnerId().equals(id) ? result.getLoserId() : result.getWinnerId())
                .collect(Collectors.toList());
    }

    private Stream<Result> resultsInvolvingPlayer() {
        return StreamSupport.stream(
                resultRepository.findAllByWinnerIdOrLoserId(id, id).spliterator(), false
        );
    }

    private Boolean notMatched(String matchRequestId) {
        return matchRepository.findByMatchRequest1IdOrMatchRequest2Id(matchRequestId, matchRequestId) == null;
    }
}
