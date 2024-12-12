package domain

import org.walid.domain.Account
import org.walid.domain.OperationType
import org.walid.domain.exceptions.InsufficientFundsException
import org.walid.domain.exceptions.InvalidAmountException
import java.math.BigDecimal

fun main() {
    println("Running Account domain unit tests...")

    testDepositPositiveAmount()
    testDepositInvalidAmount()
    testWithdrawPositiveAmount()
    testWithdrawInvalidAmount()
    testWithdrawInsufficientFunds()
    testOperationsHistory()
    testGetBalance()

    println("All Account domain unit tests passed!")
}

fun testDepositPositiveAmount() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal.ZERO)

    // Act
    account.deposit(BigDecimal("100.00"))

    // Assert
    assert(account.getBalance() == BigDecimal("100.00")) { "Test failed: Expected balance to be 100.00" }
    println("testDepositPositiveAmount passed.")
}

fun testDepositInvalidAmount() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal.ZERO)

    // Act & Assert
    try {
        account.deposit(BigDecimal("-50.00"))
        throw AssertionError("Test failed: Expected InvalidAmountException for negative deposit.")
    } catch (e: InvalidAmountException) {
        assert(e.message == "Deposit amount must be positive. Provided: -50.00") {
            "Test failed: Unexpected exception message: ${e.message}"
        }
    }
    println("testDepositInvalidAmount passed.")
}

fun testWithdrawPositiveAmount() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal("100.00"))

    // Act
    account.withdraw(BigDecimal("50.00"))

    // Assert
    assert(account.getBalance() == BigDecimal("50.00")) { "Test failed: Expected balance to be 50.00" }
    println("testWithdrawPositiveAmount passed.")
}

fun testWithdrawInvalidAmount() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal("100.00"))

    // Act & Assert
    try {
        account.withdraw(BigDecimal("-20.00"))
        throw AssertionError("Test failed: Expected InvalidAmountException for negative withdrawal.")
    } catch (e: InvalidAmountException) {
        assert(e.message == "Withdrawal amount must be positive. Provided: -20.00") {
            "Test failed: Unexpected exception message: ${e.message}"
        }
    }
    println("testWithdrawInvalidAmount passed.")
}

fun testWithdrawInsufficientFunds() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal("100.00"))

    // Act & Assert
    try {
        account.withdraw(BigDecimal("200.00"))
        throw AssertionError("Test failed: Expected InsufficientFundsException for overdrawn withdrawal.")
    } catch (e: InsufficientFundsException) {
        assert(e.message == "Insufficient funds. Balance: 100.00, Withdrawal: 200.00") {
            "Test failed: Unexpected exception message: ${e.message}"
        }
    }
    println("testWithdrawInsufficientFunds passed.")
}

fun testOperationsHistory() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal("100.00"))
    account.deposit(BigDecimal("50.00"))
    account.withdraw(BigDecimal("20.00"))

    // Act
    val operations = account.getOperations()

    // Assert
    assert(operations.size == 2) { "Test failed: Expected 2 operations, found ${operations.size}" }
    assert(operations[0].type == OperationType.DEPOSIT && operations[0].amount == BigDecimal("50.00")) {
        "Test failed: First operation does not match expected deposit."
    }
    assert(operations[1].type == OperationType.WITHDRAWAL && operations[1].amount == BigDecimal("20.00")) {
        "Test failed: Second operation does not match expected withdrawal."
    }
    println("testOperationsHistory passed.")
}

fun testGetBalance() {
    // Arrange
    val account = Account("ACC001", "USER001", BigDecimal("100.00"))
    account.deposit(BigDecimal("50.00"))
    account.withdraw(BigDecimal("20.00"))

    // Act
    val balance = account.getBalance()

    // Assert
    assert(balance == BigDecimal("130.00")) { "Test failed: Expected balance to be 130.00, but got $balance" }
    println("testGetBalance passed.")
}
