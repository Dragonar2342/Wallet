package org.example.wallet.service;

import jakarta.validation.Valid;
import org.example.wallet.entity.Wallet;
import org.example.wallet.enums.OperationType;
import org.example.wallet.exception.CustomException;
import org.example.wallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public String getWallets() {
        return walletRepository.findAll().toString();
    }

    public Optional<Wallet> getWallet(@PathVariable UUID id) {
        return walletRepository.findById(id);
    }

    public Wallet updateWallet(@PathVariable UUID id, @Valid OperationType operationType, double amount) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new CustomException("Wallet not found"));
        synchronized (wallet) {
            switch (operationType) {
                case DEPOSIT: {
                    wallet.setBalance(wallet.getBalance() + amount);
                    break;
                }
                case WITHDRAW: {
                    if (wallet.getBalance() < amount)
                        throw new CustomException("Insufficient balance");
                    else wallet.setBalance(wallet.getBalance() - amount);
                    break;
                }
                default: {
                    throw new CustomException("Invalid operation type");
                }

            }
        }
        return walletRepository.save(wallet);
    }
}
