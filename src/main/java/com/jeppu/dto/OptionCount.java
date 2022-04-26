package com.jeppu.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OptionCount {
    private Integer optionId;
    private int voteCount;

    public OptionCount() {
    }

    public OptionCount(Integer optionId, Integer voteCount) {
        this.optionId = optionId;
        this.voteCount = voteCount;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptionCount that = (OptionCount) o;

        return optionId != null ? optionId.equals(that.optionId) : that.optionId == null;
    }

    @Override
    public int hashCode() {
        return optionId != null ? optionId.hashCode() : 0;
    }
}
