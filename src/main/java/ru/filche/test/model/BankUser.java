package ru.filche.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bank_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "bankuser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Card> cards = new ArrayList<>();
}
