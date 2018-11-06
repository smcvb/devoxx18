package com.devoxx.conference.coreapi

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CreateWalletCommand(
        val walletId: String,
        val balance: Int
)

data class WithdrawCommand(
        @TargetAggregateIdentifier val walletId: String,
        val amount: Int
)

data class DepositCommand(
        @TargetAggregateIdentifier val walletId: String,
        val amount: Int
)



data class WalletCreatedEvent(
        val walletId: String,
        val balance: Int
)
data class WithdrawnEvent(
        val walletId: String,
        val amount: Int
)
data class DepositedEvent(
        val walletId: String,
        val amount: Int
)

class NotEnoughFundsException : Exception()

class FindAllWalletsQuery