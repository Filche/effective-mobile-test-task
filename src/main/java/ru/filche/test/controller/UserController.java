package ru.filche.test.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.filche.test.dto.CardDTO;
import ru.filche.test.dto.TransferRequest;
import ru.filche.test.entity.BankUser;
import ru.filche.test.service.CardService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final CardService cardService;

    @GetMapping("/cards")
    public ResponseEntity<Page<CardDTO>> getCards(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10) Pageable pageable) {
        BankUser user = (BankUser) userDetails;
        Page<CardDTO> cards = cardService.getUserCards(user.getId(), pageable);
        return ResponseEntity.ok(cards);
    }

    @PostMapping("/transfer")
    public ResponseEntity<CardDTO> transfer(@AuthenticationPrincipal BankUser user, @RequestBody TransferRequest request) {
        CardDTO result = cardService.transferMoney(user.getId(), request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cards/{number}/block")
    public ResponseEntity<Void> blockCard(@AuthenticationPrincipal BankUser user, @PathVariable String number) {
        cardService.blockCard(user.getId(), number);
        return ResponseEntity.ok().build();
    }
}