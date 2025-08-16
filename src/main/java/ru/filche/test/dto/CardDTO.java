package ru.filche.test.dto;

import lombok.Data;
import ru.filche.test.model.CardStatus;

import java.math.BigDecimal;

@Data
public class CardDTO {
    private String maskedNumber;
    private String owner;
    private String expireDate;
    private CardStatus status;
    private BigDecimal balance;
}
