package com.jeppu.dto;

import java.util.*;

public class VoteResult {
    private Integer totalVotes;
    private Collection<OptionCount> optionCounts ;

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Collection<OptionCount> getOptionCounts() {
        return optionCounts;
    }

    public void setOptionCounts(Collection<OptionCount> optionCounts) {
        this.optionCounts = optionCounts;
    }
}
