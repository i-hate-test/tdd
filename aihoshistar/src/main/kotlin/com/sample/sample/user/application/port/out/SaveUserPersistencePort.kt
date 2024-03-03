package com.aihoshistar.sample.user.application.port.out

import com.aihoshistar.sample.user.domain.model.User

interface SaveUserPersistencePort {
    fun save(user: User): User
}
