package org.pongmatcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class MatchRequestController {

    @RequestMapping("/match_requests/{id}")
    public MatchRequest matchRequest(@PathVariable("id") String id) {
        return new MatchRequest(id, "foobar");
    }
}
