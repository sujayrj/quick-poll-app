package com.jeppu.controller;

import com.jeppu.dto.OptionCount;
import com.jeppu.dto.VoteResult;
import com.jeppu.model.Vote;
import com.jeppu.repositories.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping(value = "/{pollId}/computeResult")
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;


    @GetMapping
    public ResponseEntity<?> computeResult(@PathVariable Integer pollId) {
        Iterable<Vote> votesIterable = voteRepository.findByPoll(pollId);
        Iterator<Vote> voteIterator = votesIterable.iterator();
        VoteResult voteResult = new VoteResult();
        Map<Integer, OptionCount> tempMap = new HashMap<>();
        int totalVotes = 0;
        for(Vote vote : votesIterable){
            totalVotes++;
            OptionCount optionCount = tempMap.get(vote.getOption().getId());
            if(optionCount==null){
                optionCount = new OptionCount();
                optionCount.setOptionId(vote.getOption().getId());
                tempMap.put(vote.getOption().getId(), optionCount);
            }
            optionCount.setVoteCount(optionCount.getVoteCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setOptionCounts(tempMap.values());
        return new ResponseEntity<>(voteResult, HttpStatus.FOUND);
    }

    /*@GetMapping
    public ResponseEntity<?> computeResult(@PathVariable Integer pollId) {
        Iterable<Vote> votesIterable = voteRepository.findByPoll(pollId);
        Iterator<Vote> voteIterator = votesIterable.iterator();
        VoteResult voteResult = new VoteResult();
        int totalVotes = 0;
        while (voteIterator.hasNext()){
            totalVotes++;
            Vote vote = voteIterator.next();
            if(voteResult.getOptionVoteCountMap().containsKey(vote.getOption().getId())){
                Integer oldVoteCount = voteResult.getOptionVoteCountMap().get(vote.getOption().getId());
                voteResult.getOptionVoteCountMap().put(vote.getOption().getId(), ++oldVoteCount);
            }else{
                voteResult.getOptionVoteCountMap().put(vote.getOption().getId(), 1);
            }
        }
        voteResult.setTotalVotes(totalVotes);
        return new ResponseEntity<>(voteResult, HttpStatus.FOUND);
    }*/
}
