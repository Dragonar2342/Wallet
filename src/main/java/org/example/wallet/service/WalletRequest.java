package org.example.wallet.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.example.wallet.enums.OperationType;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletRequest {
    @NonNull
    @JsonProperty("wallet_id")
    private UUID wallet_id;
    @NonNull
    @JsonProperty("operationType")
    private OperationType operationType;
    @Positive
    @JsonProperty("amount")
    private double amount;
}
