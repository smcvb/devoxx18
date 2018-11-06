package com.devoxx.conference.querymodel;

import com.devoxx.conference.coreapi.FindAllWalletsQuery;
import com.devoxx.conference.coreapi.WalletCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @QueryHandler
    public List<WalletView> answer(FindAllWalletsQuery query) {
        return walletViewRepository.findAll();
    }

}
