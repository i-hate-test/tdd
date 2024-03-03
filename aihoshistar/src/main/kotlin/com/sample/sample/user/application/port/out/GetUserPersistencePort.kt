package com.aihoshistar.sample.user.application.port.out

import com.aihoshistar.sample.user.domain.model.User

interface GetUserPersistencePort {
    fun findByEmailOrNickname(email: String, nickname: String): User?

    fun findAllByIdIn(userIds: List<Long>): List<User>

    fun findById(id: Long): User?
}
