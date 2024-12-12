package org.walid.infrastructure.db

import org.walid.infrastructure.dto.AccountDTO

class InMemoryAccountRepository : AccountRepository {
    private val accounts = mutableListOf<AccountDTO>()

    override fun getAllAccounts(): List<AccountDTO> = accounts.toList()

    override fun getAccountByNumber(accountNumber: String): AccountDTO? = accounts.find { it.accountNumber == accountNumber }

    override fun updateAccount(account: AccountDTO): Boolean {
        val index = accounts.indexOfFirst { it.accountNumber == account.accountNumber }
        return if (index >= 0) {
            accounts[index] = account
            true
        } else {
            false
        }
    }

    override fun addAccount(account: AccountDTO) {
        if (accounts.none { it.accountNumber == account.accountNumber }) {
            accounts.add(account)
        } else {
            throw IllegalArgumentException("Account already exists.")
        }
    }
}
