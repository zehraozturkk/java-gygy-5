package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "phone", length = 20)
    private String phone;

    @OneToMany(mappedBy = "student")
    private Set<Barrow> borrows;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Set<Barrow> getBorrows() { return borrows; }
    public void setBorrows(Set<Barrow> borrows) { this.borrows = borrows; }
}
