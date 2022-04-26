package com.jeppu.controller;

import com.jeppu.model.Poll;
import com.jeppu.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@RequestMapping("/polls")
public class PollController {
    @Autowired
    private PollRepository pollRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllPolls(){
        Iterable<Poll> allPolls = pollRepository.findAll();
        System.out.println("*** PollController::getAllPolls *** called");
        System.out.println(allPolls);
        return new ResponseEntity<>(allPolls, HttpStatus.FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        Poll savedPoll = pollRepository.save(poll);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{pollId}")
                .buildAndExpand(savedPoll.getId())
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        System.out.println("PollController::createPoll => "+savedPoll);
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<?> getPollById(@PathVariable("pollId") Integer id){
        Optional<Poll> pollOptional = pollRepository.findById(id);
        if(pollOptional.isPresent())
            return new ResponseEntity<>(pollOptional.get(), HttpStatus.FOUND);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{pollId}")
    public ResponseEntity<?> updatePollById(@PathVariable("pollId") Integer id, @RequestBody Poll poll) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        Poll savedPoll;
        if (optionalPoll.isPresent()) {
            savedPoll = optionalPoll.get();
            savedPoll.setQuestion(poll.getQuestion());
            savedPoll.setOptions(poll.getOptions());
            pollRepository.save(savedPoll);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/{pollId}")
                    .buildAndExpand(savedPoll.getId())
                    .toUri();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
