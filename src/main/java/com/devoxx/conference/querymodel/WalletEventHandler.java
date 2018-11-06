package com.devoxx.conference.querymodel;

import com.devoxx.conference.coreapi.WalletCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class WalletEventHandler {

    private final WalletViewRepository walletViewRepository;

    public WalletEventHandler(WalletViewRepository walletViewRepository) {
        this.walletViewRepository = walletViewRepository;
    }

    @EventHandler
    public void on(WalletCreatedEvent event) {
        walletViewRepository.save(new WalletView(event.getWalletId(), event.getBalance()));
    }

}
