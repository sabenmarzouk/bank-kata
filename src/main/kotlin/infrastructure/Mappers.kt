package org.walid.infrastructure

import org.walid.domain.Account
import org.walid.domain.Operation
import org.walid.domain.OperationType
import org.walid.infrastructure.dto.AccountDTO
import org.walid.infrastructure.dto.OperationDTO

fun AccountDTO.toDomain(): Account =
    Account(
        accountNumber = this.accountNumber,
        userId = this.userId,
        balance = this.balance,
        operations = this.operations.map { it.toDomain() }.toMutableList(),
    )

fun OperationDTO.toDomain(): Operation =
    Operation(
        type = OperationType.valueOf(this.type),
        amount = this.amount,
        date = this.date,
        balanceAfterOperation = this.balanceAfterOperation,
    )

fun Account.toDTO(): AccountDTO =
    AccountDTO(
        accountNumber = this.accountNumber,
        userId = this.userId,
        balance = this.getBalance(),
        operations = this.getOperations().map { it.toDTO() },
    )

fun Operation.toDTO(): OperationDTO =
    OperationDTO(
        type = this.type.name,
        amount = this.amount,
        date = this.date,
        balanceAfterOperation = this.balanceAfterOperation,
    )
