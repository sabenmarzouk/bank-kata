package org.walid.application

import org.walid.domain.Account
import org.walid.domain.Operation
import org.walid.infrastructure.db.AccountRepository
import org.walid.infrastructure.toDTO
import org.walid.infrastructure.toDomain
import java.math.BigDecimal

class AccountServiceImpl(
    private val repository: AccountRepository,
) : AccountService {
    @Synchronized
    override fun deposit(
        accountNumber: String,
        amount: BigDecimal,
    ) {
        val account =
            repository.getAccountByNumber(accountNumber)?.toDomain()
                ?: throw AccountNotFoundException("Account not found.")
        account.deposit(amount)
        repository.updateAccount(account.toDTO())
    }

    @Synchronized
    override fun withdraw(
        accountNumber: String,
        amount: BigDecimal,
    ) {
        val account =
            repository.getAccountByNumber(accountNumber)?.toDomain()
                ?: throw AccountNotFoundException("Account not found.")
        account.withdraw(amount)
        repository.updateAccount(account.toDTO())
    }

    override fun getAccount(accountNumber: String): Account =
        repository.getAccountByNumber(accountNumber)?.toDomain()
            ?: throw AccountNotFoundException("Account not found.")

    override fun addAccount(account: Account) {
        repository.addAccount(account.toDTO())
    }

    override fun getBalance(accountNumber: String): BigDecimal = getAccount(accountNumber).getBalance()

    override fun getAccountOperations(accountNumber: String): List<Operation> = getAccount(accountNumber).getOperations()
}
