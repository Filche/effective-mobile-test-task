package ru.filche.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.filche.test.dto.CardDTO;
import ru.filche.test.entity.BankUser;
import ru.filche.test.service.CardService;

@RestController
@RequestMapping("/api/bank-user")
@RequiredArgsConstructor
public class BankUserController {
    private final CardService cardService;

    @GetMapping("/cards")
    public ResponseEntity<Page<CardDTO>> getCards(){
        return ResponseEntity.ok(null);
    }
}
