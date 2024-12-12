package org.walid.domain.exceptions

open class DomainException(
    message: String,
) : RuntimeException(message)

class InvalidAmountException(
    message: String,
) : DomainException(message)

class InsufficientFundsException(
    message: String,
) : DomainException(message)
