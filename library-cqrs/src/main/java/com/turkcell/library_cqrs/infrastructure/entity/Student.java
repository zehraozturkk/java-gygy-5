package com.turkcell.library_cqrs.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100) private String name;
    @Column(nullable = false, length = 100) private String surname;
    @Column(length = 20)                    private String phone;
}
