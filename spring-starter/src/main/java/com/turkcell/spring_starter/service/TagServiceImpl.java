package com.turkcell.spring_starter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateTagRequest;
import com.turkcell.spring_starter.dto.CreatedTagResponse;
import com.turkcell.spring_starter.dto.ListTagRespone;
import com.turkcell.spring_starter.dto.UpdateTagRequest;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.TagRepository;

@Service
public class TagServiceImpl {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public CreatedTagResponse create(CreateTagRequest createTagRequest) {
        Tag tag = new Tag();
        tag.setName(createTagRequest.getName());

        tag = tagRepository.save(tag);

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public ListTagRespone getAll() {
        List<Tag> tags = tagRepository.findAll();
        ListTagRespone response = new ListTagRespone();
        response.setTags(tags);
        return response;
    }

    public CreatedTagResponse getById(String id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public CreatedTagResponse update(String id, UpdateTagRequest updateTagRequest) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));

        tag.setName(updateTagRequest.getName());
        tag = tagRepository.save(tag);

        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());

        return response;
    }

    public void delete(String id) {
        tagRepository.deleteById(id);
    }

}
