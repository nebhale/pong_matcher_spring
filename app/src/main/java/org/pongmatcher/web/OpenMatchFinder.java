package org.pongmatcher.web;

import org.pongmatcher.domain.MatchRequest;

import java.util.Optional;

interface OpenMatchFinder {

    Optional<MatchRequest> firstOpenMatchRequest(String id);

}
