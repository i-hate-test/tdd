package com.aihoshistar.sample.user.application.port.`in`

import com.aihoshistar.sample.user.domain.model.User

data class GetUserQuery(val userId: Long)

interface GetUserUseCase {
    fun execute(query: GetUserQuery): User
}
