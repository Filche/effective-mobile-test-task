package ru.filche.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.filche.test.repository.BankUserRepository;
import ru.filche.test.repository.CardRepository;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final BankUserRepository bankUserRepository;

}
