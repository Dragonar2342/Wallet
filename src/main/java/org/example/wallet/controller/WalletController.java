package org.example.wallet.controller;


import jakarta.validation.Valid;
import org.example.wallet.entity.Wallet;
import org.example.wallet.enums.OperationType;
import org.example.wallet.repositories.WalletRepository;
import org.example.wallet.service.WalletRequest;
import org.example.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/wallets")
    public String getAllWallets() {
        return walletService.getWallets();
    }

    @PostMapping("/edit")
    public ResponseEntity<Wallet> performOperation(@Valid @RequestBody WalletRequest request) {
        Wallet updatedWallet = walletService.updateWallet(request.getId(), request.getOperationType(), request.getBalance());
        return ResponseEntity.ok(updatedWallet);
    }

    @GetMapping("wallet/{walletId}")
    public @ResponseBody Optional<Wallet> getWallet(@PathVariable UUID walletId) {
        return walletService.getWallet(walletId);
        //return wallet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
