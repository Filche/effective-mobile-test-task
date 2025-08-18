package ru.filche.test.dto;

import org.springframework.stereotype.Component;
import ru.filche.test.entity.Card;
import ru.filche.test.util.CryptoUtil;

@Component
public class CardMapper {

    public CardDTO toDto(Card card, CryptoUtil crypto) {
        String decrypted = crypto.decrypt(card.getNumber());
        String masked = "**** **** **** " + decrypted.substring(decrypted.length() - 4);
        CardDTO dto = new CardDTO();
        dto.setMaskedNumber(masked);
        dto.setOwner(card.getOwner());
        dto.setExpireDate(card.getExpireDate());
        dto.setStatus(card.getStatus());
        dto.setBalance(card.getBalance());
        return dto;
    }
}
