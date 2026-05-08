package com.turkcell.library_cqrs.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fines")
public class Fine {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barrow_id", nullable = false)
    private Barrow barrow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @Column(name = "amount", nullable = false, precision = 8, scale = 2)
    private BigDecimal amount;

    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid = false;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Barrow getBarrow() { return barrow; }
    public void setBarrow(Barrow barrow) { this.barrow = barrow; }

    public Officer getOfficer() { return officer; }
    public void setOfficer(Officer officer) { this.officer = officer; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean isPaid) { this.isPaid = isPaid; }
}
