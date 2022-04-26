package com.jeppu.repositories;

import com.jeppu.model.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface PollRepository extends CrudRepository<Poll, Integer> {
}
