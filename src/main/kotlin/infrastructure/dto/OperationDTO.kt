package org.walid.infrastructure.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class OperationDTO(
    val type: String,
    val amount: BigDecimal,
    val date: LocalDateTime,
    val balanceAfterOperation: BigDecimal,
)
