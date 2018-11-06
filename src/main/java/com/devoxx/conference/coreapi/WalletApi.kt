package com.devoxx.conference.coreapi

data class CreateWalletCommand(
        val walletId: String,
        val balance: Int
)

data class WithdrawCommand(
        val walletId: String,
        val amount: Int
)

data class DepositCommand(
        val walletId: String,
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