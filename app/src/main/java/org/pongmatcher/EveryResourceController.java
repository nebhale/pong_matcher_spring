package org.pongmatcher;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class EveryResourceController {

    @RequestMapping(value = "/all", method = RequestMethod.DELETE)
    public @ResponseBody void delete() {
    }
}

