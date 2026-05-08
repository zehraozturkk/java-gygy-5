package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

// "barrow" = ödünç alma kaydı
@Entity
@Table(name = "borrows")
public class Barrow {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "barrow_date", nullable = false)
    private LocalDate barrowDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    // "borrowed" | "returned" | "overdue"
    @Column(name = "status", nullable = false, length = 20)
    private String status = "borrowed";

    @OneToMany(mappedBy = "barrow")
    private Set<Fine> fines;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Officer getOfficer() { return officer; }
    public void setOfficer(Officer officer) { this.officer = officer; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public LocalDate getBarrowDate() { return barrowDate; }
    public void setBarrowDate(LocalDate barrowDate) { this.barrowDate = barrowDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Set<Fine> getFines() { return fines; }
    public void setFines(Set<Fine> fines) { this.fines = fines; }
}
