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
    @JsonProperty("id")
    private UUID id;
    @NonNull
    @JsonProperty("OperationType")
    private OperationType OperationType;
    @Positive
    @JsonProperty("Balance")
    private double Balance;
}
