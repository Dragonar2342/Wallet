package org.example.wallet.dto;

import lombok.AllArgsConstructor;
import org.example.wallet.entity.Wallet;

import java.util.UUID;

@AllArgsConstructor
public class WalletDTO {
    public WalletDTO(UUID id, double balance) {
    }

    public WalletDTO getWalletDTO(Wallet wallet) {
        return new WalletDTO(wallet.getId(), wallet.getBalance());
    }
}
