package org.walid.infrastructure.dto

import java.math.BigDecimal

data class AccountDTO(
    val accountNumber: String,
    val userId: String,
    val balance: BigDecimal,
    val operations: List<OperationDTO>,
)
