package org.pongmatcher.controllers;

import org.pongmatcher.domain.*;
import org.pongmatcher.repositories.MatchRepository;
import org.pongmatcher.repositories.MatchRequestRepository;
import org.pongmatcher.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class MatchRequestController {

    @Autowired
    MatchRequestController(MatchRequestRepository matchRequestRepository, MatchRepository matchRepository, ResultRepository resultRepository) {
        this.matchRequestRepository = matchRequestRepository;
        this.matchRepository = matchRepository;
        this.resultRepository = resultRepository;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class ResourceNotFoundException extends RuntimeException {
    }

    private final MatchRequestRepository matchRequestRepository;

    private final MatchRepository matchRepository;

    private final ResultRepository resultRepository;

    @RequestMapping(value = "/match_requests/{id}", method = RequestMethod.GET)
    public MatchRequest show(@PathVariable("id") String id) {
        Match match = matchRepository.findByMatchRequest1IdOrMatchRequest2Id(id, id);

        if (match == null) {
            throw new ResourceNotFoundException();
        } else {
            return new FulfilledMatchRequest(match.getId());
        }
    }

    @Transactional
    @RequestMapping(value = "/match_requests/{id}", method = RequestMethod.PUT)
    public MatchRequest save(@PathVariable("id") String id,
                             @RequestBody NewMatchRequest proposedMatchRequest) {
        MatchRequest persistingMatchRequest = new MatchRequest(id, proposedMatchRequest.getRequesterId());
        matchRequestRepository.save(persistingMatchRequest);

        Player player = new Player(
                persistingMatchRequest.getRequesterId(),
                matchRepository,
                matchRequestRepository,
                resultRepository
        );

        Optional<MatchRequest> openMatchRequest = player.firstOpenMatchRequest();

        if (openMatchRequest.isPresent()) {
            recordMatch(openMatchRequest.get(), persistingMatchRequest);
        }

        return persistingMatchRequest;
    }

    private void recordMatch(MatchRequest openMatchRequest, MatchRequest newMatchRequest) {
        matchRepository.save(
                new Match(UUID.randomUUID().toString(), openMatchRequest.getUuid(), newMatchRequest.getUuid())
        );
    }
}
