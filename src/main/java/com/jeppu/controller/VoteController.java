package com.jeppu.controller;

import com.jeppu.model.Vote;
import com.jeppu.repositories.VoteRepository;
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
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    @GetMapping("/{pollId}/votes")
    public ResponseEntity<?> getAllVotes(@PathVariable("pollId") Integer pollId) {
        Iterable<Vote> allVotes = voteRepository.findAll();
        return new ResponseEntity<>(allVotes, HttpStatus.FOUND);
    }

    @PostMapping("/{pollId}/votes")
    public ResponseEntity<?> createVote(@RequestBody Vote vote, @PathVariable("pollId") Integer pollId) {
        Vote savedVote = voteRepository.save(vote);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{voteId}")
                .buildAndExpand(savedVote.getId())
                .toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        System.out.println("VoteController::createVote => "+savedVote);
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @GetMapping("/{pollId}/votes/{voteId}")
    public ResponseEntity<?> getVoteById(@PathVariable("voteId") Integer id){
        Optional<Vote> optionalVote = voteRepository.findById(id);
        if(optionalVote.isPresent())
            return new ResponseEntity<>(optionalVote.get(), HttpStatus.FOUND);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
