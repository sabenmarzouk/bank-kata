package org.walid.application

import org.walid.domain.Account
import org.walid.domain.Operation
import java.math.BigDecimal

interface AccountService {
    fun deposit(
        accountNumber: String,
        amount: BigDecimal,
    )

    fun withdraw(
        accountNumber: String,
        amount: BigDecimal,
    )

    fun getAccount(accountNumber: String): Account

    fun addAccount(account: Account)

    fun getBalance(accountNumber: String): BigDecimal

    fun getAccountOperations(accountNumber: String): List<Operation>
}
