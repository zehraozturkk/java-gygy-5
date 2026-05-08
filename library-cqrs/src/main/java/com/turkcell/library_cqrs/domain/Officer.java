package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "officers")
public class Officer {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @OneToMany(mappedBy = "officer")
    private Set<Barrow> borrows;

    @OneToMany(mappedBy = "officer")
    private Set<Fine> fines;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<Barrow> getBorrows() { return borrows; }
    public void setBorrows(Set<Barrow> borrows) { this.borrows = borrows; }

    public Set<Fine> getFines() { return fines; }
    public void setFines(Set<Fine> fines) { this.fines = fines; }
}
