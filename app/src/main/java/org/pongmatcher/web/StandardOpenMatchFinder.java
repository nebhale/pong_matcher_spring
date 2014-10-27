package org.pongmatcher.web;

import org.pongmatcher.domain.MatchRequest;
import org.pongmatcher.domain.Result;
import org.pongmatcher.repositories.MatchRepository;
import org.pongmatcher.repositories.MatchRequestRepository;
import org.pongmatcher.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Qualifier("caching")
final class StandardOpenMatchFinder implements OpenMatchFinder {

    private final MatchRepository matchRepository;

    private final MatchRequestRepository matchRequestRepository;

    private final ResultRepository resultRepository;

    @Autowired
    StandardOpenMatchFinder(MatchRepository matchRepository, MatchRequestRepository matchRequestRepository,
                            ResultRepository resultRepository) {
        this.matchRepository = matchRepository;
        this.matchRequestRepository = matchRequestRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public Optional<MatchRequest> firstOpenMatchRequest(String id) {
        return unfulfilledMatchRequests()
                .filter(request -> !inappropriateOpponents(id).contains(request.getRequesterId()))
                .findFirst();
    }

    private Stream<MatchRequest> unfulfilledMatchRequests() {
        List<MatchRequest> allRequests = matchRequestRepository.findByOrderByIdAsc();
        return allRequests.stream().filter(request -> notMatched(request.getUuid()));
    }

    private List<String> inappropriateOpponents(String id) {
        List<String> list = previousOpponents(id);
        list.add(id);
        return list;
    }

    private List<String> previousOpponents(String id) {
        return resultsInvolvingPlayer(id)
                .map(result -> result.getWinnerId().equals(id) ? result.getLoserId() : result.getWinnerId())
                .collect(Collectors.toList());
    }

    private Stream<Result> resultsInvolvingPlayer(String id) {
        return resultRepository.findAllByWinnerIdOrLoserId(id, id).stream();
    }

    private Boolean notMatched(String matchRequestId) {
        return matchRepository.findByMatchRequest1IdOrMatchRequest2Id(matchRequestId, matchRequestId) == null;
    }

}
