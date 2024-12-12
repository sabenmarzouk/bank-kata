package org.walid.application

open class ServiceException(
    message: String,
) : RuntimeException(message)

class AccountNotFoundException(
    message: String,
) : ServiceException(message)
