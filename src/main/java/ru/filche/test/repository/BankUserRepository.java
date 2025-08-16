package ru.filche.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.filche.test.entity.BankUser;

import java.util.Optional;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    Optional<BankUser> findByUsername(String username);
}
