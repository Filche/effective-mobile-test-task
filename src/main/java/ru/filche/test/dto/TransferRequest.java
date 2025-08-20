package ru.filche.test.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class TransferRequest {
    @NotNull(message = "Номер отправителя не может быть пустым")
    private String fromCardNumber;

    @NotNull(message = "Номер получателя не может быть пустым")
    private String toCardNumber;

    @NotNull(message = "Сумма перевода обязательна")
    @Positive(message = "Сумма должна быть больше 0")
    private BigDecimal amount;
}
