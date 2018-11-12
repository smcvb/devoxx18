package com.devoxx.conference.querymodel

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class WalletView(
        @Id val walletId: String? = null,
        val balance: Int? = null
)

interface WalletViewRepository : JpaRepository<WalletView, String>
