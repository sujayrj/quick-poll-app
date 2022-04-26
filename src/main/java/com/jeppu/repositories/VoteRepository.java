package com.jeppu.repositories;

import com.jeppu.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Integer> {
    @Query(value = "SELECT v.* FROM Vote v, Option o WHERE v.OPTION_ID = o.OPTION_ID AND o.POLL_ID = ?1", nativeQuery = true)
    Iterable<Vote> findByPoll(Integer pollId);
}
