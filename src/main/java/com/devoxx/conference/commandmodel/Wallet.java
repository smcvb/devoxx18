package com.devoxx.conference.commandmodel;

import com.devoxx.conference.coreapi.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class Wallet {

    @AggregateIdentifier
    private String walletId;
    private int balance;

    public Wallet() {
    }

    @CommandHandler
    public Wallet(CreateWalletCommand command) {
        AggregateLifecycle.apply(new WalletCreatedEvent(command.getWalletId(), command.getBalance()));
    }

    @CommandHandler
    public void handle(DepositCashCommand command) {
        AggregateLifecycle.apply(new CashDepositedEvent(walletId, command.getAmount()));
    }

    @CommandHandler
    public void handle(WithdrawCashCommand command) throws NotEnoughFundsException {
        int amount = command.getAmount();
        if (balance - amount < 0) {
            throw new NotEnoughFundsException();
        }
        AggregateLifecycle.apply(new CashWithdrawnEvent(walletId, amount));
    }

    @EventSourcingHandler
    public void on(WalletCreatedEvent event) {
        walletId = event.getWalletId();
        balance = event.getBalance();
    }

    @EventSourcingHandler
    public void on(CashDepositedEvent event) {
        balance = balance + event.getAmount();
    }

    @EventSourcingHandler
    public void on(CashWithdrawnEvent event) {
        balance = balance - event.getAmount();
    }
}
