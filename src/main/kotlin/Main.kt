package org.walid

import org.walid.application.AccountServiceImpl
import org.walid.domain.Account
import org.walid.infrastructure.db.InMemoryAccountRepository
import java.math.BigDecimal

fun main() {
    val repository = InMemoryAccountRepository()
    val service = AccountServiceImpl(repository)

    // Add an account
    service.addAccount(Account("ACC001", "USER001", BigDecimal("100.00")))

    // Deposit and Withdraw
    service.deposit("ACC001", BigDecimal("50.00"))
    service.withdraw("ACC001", BigDecimal("30.00"))

    // Print operations
    val operations = service.getAccountOperations("ACC001")
    operations.forEach {
        println("${it.type} - ${it.amount} on ${it.date}, Balance: ${it.balanceAfterOperation}")
    }
}
