package com.devoxx.conference.commandmodel;

import com.devoxx.conference.coreapi.CreateWalletCommand;
import com.devoxx.conference.coreapi.DepositCashCommand;
import com.devoxx.conference.coreapi.WithdrawCashCommand;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/command")
public class WalletCommandController {

    private final CommandGateway commandGateway;

    public WalletCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping
    public void createWallet() {
        String walletId = UUID.randomUUID().toString();
        commandGateway.send(new CreateWalletCommand(walletId, 1000), LoggingCallback.INSTANCE);
        commandGateway.send(new DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE);
        commandGateway.send(new DepositCashCommand(walletId, 42), LoggingCallback.INSTANCE);
        commandGateway.send(new WithdrawCashCommand(walletId, 84), LoggingCallback.INSTANCE);
//        commandGateway.send(new WithdrawCashCommand(walletId, 1337), LoggingCallback.INSTANCE);
    }
}
