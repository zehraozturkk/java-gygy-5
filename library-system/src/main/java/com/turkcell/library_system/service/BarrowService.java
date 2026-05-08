package com.turkcell.library_system.service;

import com.turkcell.library_system.dto.*;
import com.turkcell.library_system.entity.*;
import com.turkcell.library_system.exception.BookAlreadyBorrowedException;
import com.turkcell.library_system.exception.EntityNotFoundException;
import com.turkcell.library_system.repository.BarrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BarrowService {

    private final BarrowRepository barrowRepository;
    private final StudentService studentService;
    private final OfficerService officerService;
    private final BookService bookService;

    public BarrowService(BarrowRepository barrowRepository,
                         StudentService studentService,
                         OfficerService officerService,
                         BookService bookService) {
        this.barrowRepository = barrowRepository;
        this.studentService = studentService;
        this.officerService = officerService;
        this.bookService = bookService;
    }

    public List<BarrowResponse> getAll() {
        return barrowRepository.findAll().stream().map(this::toResponse).toList();
    }

    public BarrowResponse getById(Integer id) {
        return toResponse(findOrThrow(id));
    }

    public BarrowResponse create(CreateBarrowRequest request) {
        // Kitap zaten ödünçte mi kontrol et
        boolean alreadyBorrowed = barrowRepository.findByStatus("borrowed")
                .stream()
                .anyMatch(b -> b.getBook().getId().equals(request.bookId()));
        if (alreadyBorrowed) {
            throw new BookAlreadyBorrowedException(request.bookId());
        }

        Student student = studentService.getEntityById(request.studentId());
        Officer officer = officerService.getEntityById(request.officerId());
        Book book = bookService.getEntityById(request.bookId());

        Barrow barrow = new Barrow();
        barrow.setStudent(student);
        barrow.setOfficer(officer);
        barrow.setBook(book);
        barrow.setBarrowDate(LocalDate.now());
        barrow.setDueDate(request.dueDate());
        barrow.setStatus("borrowed");

        return toResponse(barrowRepository.save(barrow));
    }

    public BarrowResponse update(Integer id, UpdateBarrowRequest request) {
        Barrow barrow = findOrThrow(id);
        barrow.setReturnDate(request.returnDate());
        barrow.setStatus(request.status());
        return toResponse(barrowRepository.save(barrow));
    }

    public void delete(Integer id) {
        findOrThrow(id);
        barrowRepository.deleteById(id);
    }

    public Barrow getEntityById(Integer id) {
        return findOrThrow(id);
    }

    private Barrow findOrThrow(Integer id) {
        return barrowRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Barrow", id));
    }

    private BarrowResponse toResponse(Barrow b) {
        String studentFull = b.getStudent().getName() + " " + b.getStudent().getSurname();
        String officerFull = b.getOfficer().getName() + " " + b.getOfficer().getSurname();
        return new BarrowResponse(
                b.getId(),
                b.getStudent().getId(), studentFull,
                b.getOfficer().getId(), officerFull,
                b.getBook().getId(), b.getBook().getName(),
                b.getBarrowDate(), b.getDueDate(), b.getReturnDate(), b.getStatus()
        );
    }
}
