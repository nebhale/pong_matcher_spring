package org.pongmatcher.web;

import org.pongmatcher.domain.Match;
import org.pongmatcher.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
final class MatchesController {

    private final MatchRepository matchRepository;

    @Autowired
    MatchesController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/matches/{id}")
    Match show(@PathVariable String id) {
        return matchRepository.findOne(id);
    }

}
