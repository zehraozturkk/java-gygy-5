package com.turkcell.library_system.service;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.entity.Author;
import com.turkcell.library_system.exception.EntityNotFoundException;
import com.turkcell.library_system.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorResponse> getAll() {
        return authorRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AuthorResponse getById(Integer id) {
        return toResponse(findOrThrow(id));
    }

    public AuthorResponse create(CreateAuthorRequest request) {
        Author author = new Author();
        author.setName(request.name());
        author.setSurname(request.surname());
        return toResponse(authorRepository.save(author));
    }

    public AuthorResponse update(Integer id, UpdateAuthorRequest request) {
        Author author = findOrThrow(id);
        author.setName(request.name());
        author.setSurname(request.surname());
        return toResponse(authorRepository.save(author));
    }

    public void delete(Integer id) {
        findOrThrow(id);
        authorRepository.deleteById(id);
    }

    public Author getEntityById(Integer id) {
        return findOrThrow(id);
    }

    private Author findOrThrow(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author", id));
    }

    private AuthorResponse toResponse(Author a) {
        return new AuthorResponse(a.getId(), a.getName(), a.getSurname());
    }
}
