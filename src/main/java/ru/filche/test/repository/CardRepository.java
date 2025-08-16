package ru.filche.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.filche.test.entity.Card;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByBankUserId(Long bankUserId);
    Page<Card> findByBankUserId(Long bankUserId, Pageable pageable);
    Optional<Card> findByBankUserIdAndNumber(String number, Long bankUserId);
}
