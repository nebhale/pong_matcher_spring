package org.pongmatcher.controllers;

import org.pongmatcher.domain.*;
import org.pongmatcher.repositories.MatchRepository;
import org.pongmatcher.repositories.MatchRequestRepository;
import org.pongmatcher.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.*;
import java.util.UUID;

@RestController
public class MatchRequestController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class ResourceNotFoundException extends RuntimeException {}

    @Autowired
    private MatchRequestRepository matchRequestRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ResultRepository resultRepository;

    @RequestMapping(value = "/match_requests/{id}", method = RequestMethod.GET)
    public MatchRequest show(@PathVariable("id") String id) {
        Match match = matchRepository.findByMatchRequest1IdOrMatchRequest2Id(id, id);

        if (match == null) {
            throw new ResourceNotFoundException();
        } else {
            return new FulfilledMatchRequest(match.getId());
        }
    }

    @RequestMapping(value = "/match_requests/{id}", method = RequestMethod.PUT)
    public MatchRequest save(@PathVariable("id") String id,
                             @RequestBody NewMatchRequest proposedMatchRequest) {
        MatchRequest persistingMatchRequest = new MatchRequest(id, proposedMatchRequest.getRequesterId());
        this.matchRequestRepository.save(persistingMatchRequest);

        Optional<MatchRequest> openMatchRequest = firstOpenMatchRequest(persistingMatchRequest.getRequesterId());

        if (openMatchRequest.isPresent()) {
            recordMatch(openMatchRequest.get(), persistingMatchRequest);
        }

        return persistingMatchRequest;
    }

    private Optional<MatchRequest> firstOpenMatchRequest(String playerId) {
        return unfulfilledMatchRequests()
                .filter(request -> !inappropriateOpponents(playerId).contains(request.getRequesterId()))
                .findFirst();
    }

    private List<String> inappropriateOpponents(String playerId) {
        List<String> list = previousOpponents(playerId);
        list.add(playerId);
        return list;
    }

    private List<String> previousOpponents(String playerId) {
        return resultsInvolvingPlayer(playerId)
                .map(result -> result.getWinnerId().equals(playerId) ? result.getLoserId() : result.getWinnerId())
                .collect(Collectors.toList());
    }

    private Stream<Result> resultsInvolvingPlayer(String playerId) {
        return StreamSupport.stream(
                resultRepository.findAllByWinnerIdOrLoserId(playerId, playerId).spliterator(), false
        );
    }

    private void recordMatch(MatchRequest openMatchRequest, MatchRequest newMatchRequest) {
        matchRepository.save(
                new Match(UUID.randomUUID().toString(), openMatchRequest.getId(), newMatchRequest.getId())
        );
    }

    private Stream<MatchRequest> unfulfilledMatchRequests() {
        Iterable<MatchRequest> allRequests = matchRequestRepository.findAll();
        return StreamSupport.stream(allRequests.spliterator(), false)
                .filter(request -> notMatched(request.getId()));
    }

    private Boolean notMatched(String matchRequestId) {
        return matchRepository.findByMatchRequest1IdOrMatchRequest2Id(matchRequestId, matchRequestId) == null;
    }
}
