
import org.walid.application.AccountServiceImpl
import org.walid.domain.Account
import org.walid.domain.OperationType
import org.walid.infrastructure.db.InMemoryAccountRepository
import org.walid.infrastructure.toDTO
import java.math.BigDecimal

fun main() {
    println("Running Bdd tests...")

    bddTestDepositScenario()
    bddTestWithdrawScenario()
    bddTestBalanceCheckScenario()
    bddTestOperationHistoryScenario()

    println("All bdd tests passed!")
}

fun bddTestDepositScenario() {
    // Arrange
    val repository = InMemoryAccountRepository()
    val service = AccountServiceImpl(repository)
    val account = Account("ACC001", "USER001", BigDecimal("100.00"))
    repository.addAccount(account.toDTO())

    // Act
    service.deposit("ACC001", BigDecimal("50.00"))

    // Assert
    val updatedAccount = service.getAccount("ACC001")
    assert(updatedAccount.getBalance() == BigDecimal("150.00")) {
        "Test failed: Expected balance to be 150.00, found ${updatedAccount.getBalance()}"
    }
    println("bddTestDepositScenario passed.")
}

fun bddTestWithdrawScenario() {
    // Arrange
    val repository = InMemoryAccountRepository()
    val service = AccountServiceImpl(repository)
    val account = Account("ACC002", "USER002", BigDecimal("200.00"))
    service.addAccount(account)

    // Act
    service.withdraw("ACC002", BigDecimal("50.00"))

    // Assert
    val updatedAccount = service.getAccount("ACC002")
    assert(updatedAccount.getBalance() == BigDecimal("150.00")) {
        "Test failed: Expected balance to be 150.00, found ${updatedAccount.getBalance()}"
    }
    println("bddTestWithdrawScenario passed.")
}

fun bddTestBalanceCheckScenario() {
    // Arrange
    val repository = InMemoryAccountRepository()
    val service = AccountServiceImpl(repository)
    val account = Account("ACC003", "USER003", BigDecimal("500.00"))
    service.addAccount(account)

    // Act
    val balance = service.getBalance("ACC003")

    // Assert
    assert(balance == BigDecimal("500.00")) { "Test failed: Expected balance to be 500.00, found $balance" }
    println("bddTestBalanceCheckScenario passed.")
}

fun bddTestOperationHistoryScenario() {
    // Arrange
    val repository = InMemoryAccountRepository()
    val service = AccountServiceImpl(repository)
    val account = Account("ACC004", "USER004", BigDecimal("300.00"))
    service.addAccount(account)

    // Act
    service.deposit("ACC004", BigDecimal("100.00"))
    service.withdraw("ACC004", BigDecimal("50.00"))
    val operations = service.getAccountOperations("ACC004")

    // Assert
    assert(operations.size == 2) { "Test failed: Expected 2 operations, found ${operations.size}" }
    assert(operations[0].type == OperationType.DEPOSIT && operations[0].amount == BigDecimal("100.00")) {
        "Test failed: First operation is not a deposit of 100.00."
    }
    assert(operations[1].type == OperationType.WITHDRAWAL && operations[1].amount == BigDecimal("50.00")) {
        "Test failed: Second operation is not a withdrawal of 50.00."
    }
    println("bddTestOperationHistoryScenario passed.")
}
