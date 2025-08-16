package ru.filche.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "expire_date", nullable = false)
    private String expireDate;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "bank_user", nullable = false)
    private BankUser bankUser;
}
