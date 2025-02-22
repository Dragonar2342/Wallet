package org.example.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.wallet.controller.WalletController;
import org.example.wallet.enums.OperationType;
import org.example.wallet.service.WalletRequest;
import org.example.wallet.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WalletApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WalletService walletService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testDeposit() throws Exception {
        UUID walletId = UUID.fromString("c8ce67f5-95e0-4ceb-afad-20416cfaf908");
        WalletRequest request = new WalletRequest();
        request.setWallet_id(walletId);
        request.setOperationType(OperationType.valueOf("DEPOSIT"));
        request.setAmount(1000.0);

        doThrow(new RuntimeException("Deposit failed")).when(walletService).updateWallet(any(UUID.class),
                any(OperationType.class), any(Double.class));

        mockMvc.perform(post("/api/v1/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithdraw() throws Exception {
        UUID walletId = UUID.fromString("c8ce67f5-95e0-4ceb-afad-20416cfaf908");
        WalletRequest request = new WalletRequest();
        request.setWallet_id(walletId);
        request.setOperationType(OperationType.valueOf("WITHDRAW"));
        request.setAmount(500.0);

        doThrow(new RuntimeException("Withdraw failed")).when(walletService).updateWallet(any(UUID.class),
                any(OperationType.class), any(Double.class));

        mockMvc.perform(post("/api/v1/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
