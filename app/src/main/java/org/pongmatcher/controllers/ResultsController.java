package org.pongmatcher.controllers;

import org.pongmatcher.domain.NewResult;
import org.pongmatcher.domain.Result;
import org.pongmatcher.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class ResultsController {

    private final ResultRepository resultRepository;

    @Autowired
    ResultsController(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Transactional
    @RequestMapping(value = "/results", method = RequestMethod.POST)
    public ResponseEntity<Result> save(@RequestBody NewResult newResult) {
        Result result = new Result(
                UUID.randomUUID().toString(),
                newResult.getWinnerId(),
                newResult.getLoserId(),
                newResult.getMatchId()
        );
        resultRepository.save(result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
