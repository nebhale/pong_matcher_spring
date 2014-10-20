package org.pongmatcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class EveryResourceController {

    @Autowired
    private MatchRequestRepository matchRequestRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ResultRepository resultRepository;

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public @ResponseBody void delete() {
        matchRequestRepository.deleteAll();
        matchRepository.deleteAll();
        resultRepository.deleteAll();
    }
}

