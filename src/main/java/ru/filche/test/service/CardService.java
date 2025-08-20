package ru.filche.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.filche.test.dto.CardDTO;
import ru.filche.test.dto.CardMapper;
import ru.filche.test.dto.TransferRequest;
import ru.filche.test.entity.Card;
import ru.filche.test.entity.CardStatus;
import ru.filche.test.repository.BankUserRepository;
import ru.filche.test.repository.CardRepository;
import ru.filche.test.util.CryptoUtil;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final BankUserRepository userRepository;
    private final CryptoUtil crypto;
    private final CardMapper cardMapper;

    public Page<CardDTO> getUserCards(Long userId, Pageable pageable) {
        Page<Card> cards = cardRepository.findByBankUserId(userId, pageable);
        return cards.map(c -> cardMapper.toDto(c, crypto));
    }

    public CardDTO transferMoney(Long userId, TransferRequest request) {
        Card from = getCardByNumberAndUser(request.getFromCardNumber(), userId);
        Card to = getCardByNumberAndUser(request.getToCardNumber(), userId);

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        from.setBalance(from.getBalance().subtract(request.getAmount()));
        to.setBalance(to.getBalance().add(request.getAmount()));

        cardRepository.save(from);
        cardRepository.save(to);

        return cardMapper.toDto(to, crypto);
    }

    public void blockCard(Long userId, String cardNumber) {
        Card card = getCardByNumberAndUser(cardNumber, userId);
        card.setStatus(CardStatus.BLOCKED);
        cardRepository.save(card);
    }

    private Card getCardByNumberAndUser(String number, Long userId) {
        String encrypted = crypto.encrypt(number);
        return cardRepository.findByBankUserIdAndNumber(encrypted, userId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }
}
