package org.pongmatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.*;
import java.util.UUID;

@RestController
public class MatchRequestController {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @Autowired
    private MatchRequestRepository matchRequestRepository;
    @Autowired
    private MatchRepository matchRepository;

    @RequestMapping(value = "/match_requests/{id}", method = RequestMethod.GET)
    public MatchRequest show(@PathVariable("id") String id) {
        // nb: no unplayedness checked yet (need Results)
        Optional<Match> unplayedMatch;
        Iterable<Match> matches = this.matchRepository.findAll();

        unplayedMatch = StreamSupport.stream(matches.spliterator(), false)
                .filter(m -> m.getMatchRequest1Id().equals(id) || m.getMatchRequest2Id().equals(id))
                .findFirst();

        if (unplayedMatch.isPresent()) {
            return new FulfilledMatchRequest(unplayedMatch.get().getId());
        } else {
            throw new ResourceNotFoundException(
                    String.format(
                            "No unplayed match available for match request id: %s -- Matches available: %s",
                            id,
                        matches.toString()
                    )
            );
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
                .filter(r -> !r.getRequesterId().equals(playerId))
                .findFirst();
    }

    private void recordMatch(MatchRequest openMatchRequest, MatchRequest newMatchRequest) {
        matchRepository.save(
                new Match(UUID.randomUUID().toString(), openMatchRequest.getId(), newMatchRequest.getId())
        );
    }

    private Stream<MatchRequest> unfulfilledMatchRequests() {
        Iterable<MatchRequest> allRequests = matchRequestRepository.findAll();
        return StreamSupport.stream(allRequests.spliterator(), false);
    }
}
