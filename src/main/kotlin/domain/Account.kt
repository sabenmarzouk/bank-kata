package org.walid.domain

import org.walid.domain.exceptions.InsufficientFundsException
import org.walid.domain.exceptions.InvalidAmountException
import java.math.BigDecimal
import java.time.LocalDateTime

data class Account(
    val accountNumber: String,
    val userId: String,
    @Volatile
    private var balance: BigDecimal = BigDecimal.ZERO,
    private val operations: MutableList<Operation> = mutableListOf(),
) {
    @Synchronized
    fun deposit(amount: BigDecimal): Account {
        if (amount <= BigDecimal.ZERO) {
            throw InvalidAmountException("Deposit amount must be positive. Provided: $amount")
        }
        balance += amount
        operations.add(Operation(OperationType.DEPOSIT, amount, LocalDateTime.now(), balance))
        return this
    }

    @Synchronized
    fun withdraw(amount: BigDecimal): Account {
        if (amount <= BigDecimal.ZERO) {
            throw InvalidAmountException("Withdrawal amount must be positive. Provided: $amount")
        }
        if (amount > balance) {
            throw InsufficientFundsException("Insufficient funds. Balance: $balance, Withdrawal: $amount")
        }
        balance -= amount
        operations.add(Operation(OperationType.WITHDRAWAL, amount, LocalDateTime.now(), balance))
        return this
    }

    fun getBalance(): BigDecimal = balance

    fun getOperations(): List<Operation> = operations.toList()
}
