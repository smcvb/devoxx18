package com.devoxx.conference.querymodel

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class WalletView(
        @Id val walletId: String? = null,
        val balance: Int? = null
)
