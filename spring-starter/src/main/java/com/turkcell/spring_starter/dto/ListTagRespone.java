package com.turkcell.spring_starter.dto;

import java.util.List;

import com.turkcell.spring_starter.entity.Tag;

public class ListTagRespone {

    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
