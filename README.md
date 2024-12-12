# Bank Account Kata Project

This project implements a simple bank account management system using Kotlin, adhering to principles of Clean Architecture and Domain-Driven Design (DDD). The application supports deposit, withdrawal, balance check, and transaction history features, with a focus on modularity and testability.

---

## Project Structure

The project is divided into the following layers:

### 1. **Domain Layer**
- Contains the core business logic and domain entities (`Account`, `Operation`, `OperationType`).
- Includes domain-specific exceptions such as `InvalidAmountException` and `InsufficientFundsException`.

### 2. **Infrastructure Layer**
- Implements the database logic with an in-memory repository (`InMemoryAccountRepository`).
- Provides DTO mapping between domain objects and database objects.

### 3. **Application Layer**
- Contains the `AccountServiceImpl`, which is the service responsible for orchestrating operations between the domain and infrastructure layers.
- Implements use cases such as deposit, withdrawal, balance retrieval, and operation history.

---

## Features

1. **Deposit Money**
   - Add a specified amount to an account.
   - Validation ensures the amount is positive.

2. **Withdraw Money**
   - Deduct a specified amount from an account.
   - Validation ensures sufficient funds and a positive withdrawal amount.

3. **Check Balance**
   - Retrieve the current balance of an account.

4. **Transaction History**
   - View a list of all operations (deposits and withdrawals) performed on an account.

---

## How to Run the Application

1. **Dependencies**
   - Kotlin (1.8 or later).
   - A JVM (Java 11 or later).

2. **Build and Run**
   - Compile the application using `kotlinc`.
   - Run the `main` function in the `AccountServiceImpl` test suite for functional and BDD testing.

3. **Testing**
   - Unit tests are provided for domain logic and service layer operations.
   - BDD-style functional tests are implemented to validate scenarios such as deposits, withdrawals, balance checks, and operation history.

---

## Project Highlights

### Design Principles
- **Clean Architecture**: Separates the project into layers with clear dependencies and responsibilities.
- **Domain-Driven Design (DDD)**: Domain logic is encapsulated in domain entities and is isolated from other concerns.
- **Thread Safety**: Key operations are synchronized to ensure thread safety for critical sections such as balance updates.

### Logging
- Logging is added to track service operations for debugging and audit purposes.

---

## Sample BDD Test Scenarios

### Deposit Scenario
- **Given**: An account with an initial balance of 100.00.
- **When**: A deposit of 50.00 is made.
- **Then**: The balance becomes 150.00.

### Withdrawal Scenario
- **Given**: An account with an initial balance of 200.00.
- **When**: A withdrawal of 50.00 is made.
- **Then**: The balance becomes 150.00.

---

## Future Enhancements
- Implement a persistent database (e.g., SQL or NoSQL).
- Add RESTful APIs for external interaction.
- Introduce user authentication and authorization.

---
