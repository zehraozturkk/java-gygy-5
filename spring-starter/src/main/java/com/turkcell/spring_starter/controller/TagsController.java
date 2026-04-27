package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.CreateTagRequest;
import com.turkcell.spring_starter.dto.CreatedTagResponse;
import com.turkcell.spring_starter.dto.ListTagRespone;
import com.turkcell.spring_starter.dto.UpdateTagRequest;
import com.turkcell.spring_starter.service.TagServiceImpl;

@RestController
@RequestMapping("/api/tags")
public class TagsController {
    private final TagServiceImpl tagServiceImpl;

    public TagsController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    @PostMapping()
    public CreatedTagResponse create(@RequestBody CreateTagRequest tagRequest) {
        return tagServiceImpl.create(tagRequest);
    }

    @GetMapping()
    public ListTagRespone getAll() {
        return tagServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public CreatedTagResponse getById(@PathVariable String id) {
        return tagServiceImpl.getById(id);
    }

    @PutMapping("/{id}")
    public CreatedTagResponse update(@PathVariable String id, @RequestBody UpdateTagRequest updateTagRequest) {
        return tagServiceImpl.update(id, updateTagRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        tagServiceImpl.delete(id);
    }

}
