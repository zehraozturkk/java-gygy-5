package com.turkcell.library_cqrs.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fines")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Fine {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "barrow_id",  nullable = false)
    private Barrow barrow;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @Column(nullable = false, precision = 8, scale = 2) private BigDecimal amount;
    @Column(name = "issued_date", nullable = false)     private LocalDate issuedDate;
    @Column(name = "is_paid",     nullable = false)     private boolean isPaid = false;
}
