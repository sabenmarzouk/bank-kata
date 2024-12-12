package org.walid.infrastructure.db

import org.walid.infrastructure.dto.AccountDTO

interface AccountRepository {
    fun getAllAccounts(): List<AccountDTO>

    fun getAccountByNumber(accountNumber: String): AccountDTO?

    fun updateAccount(accountDTO: AccountDTO): Boolean

    fun addAccount(account: AccountDTO)
}
