package org.walid.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Operation(
    val type: OperationType,
    val amount: BigDecimal,
    val date: LocalDateTime,
    val balanceAfterOperation: BigDecimal,
)

enum class OperationType {
    DEPOSIT,
    WITHDRAWAL,
}
