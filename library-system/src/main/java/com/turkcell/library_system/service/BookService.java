package com.turkcell.library_system.service;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.entity.Author;
import com.turkcell.library_system.entity.Book;
import com.turkcell.library_system.exception.EntityNotFoundException;
import com.turkcell.library_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream().map(this::toResponse).toList();
    }

    public BookResponse getById(Integer id) {
        return toResponse(findOrThrow(id));
    }

    public BookResponse create(CreateBookRequest request) {
        Author author = authorService.getEntityById(request.authorId());
        Book book = new Book();
        book.setName(request.name());
        book.setAuthor(author);
        return toResponse(bookRepository.save(book));
    }

    public BookResponse update(Integer id, UpdateBookRequest request) {
        Book book = findOrThrow(id);
        Author author = authorService.getEntityById(request.authorId());
        book.setName(request.name());
        book.setAuthor(author);
        return toResponse(bookRepository.save(book));
    }

    public void delete(Integer id) {
        findOrThrow(id);
        bookRepository.deleteById(id);
    }

    public Book getEntityById(Integer id) {
        return findOrThrow(id);
    }

    private Book findOrThrow(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book", id));
    }

    private BookResponse toResponse(Book b) {
        String authorFullName = b.getAuthor().getName() + " " + b.getAuthor().getSurname();
        return new BookResponse(b.getId(), b.getName(), b.getAuthor().getId(), authorFullName);
    }
}
