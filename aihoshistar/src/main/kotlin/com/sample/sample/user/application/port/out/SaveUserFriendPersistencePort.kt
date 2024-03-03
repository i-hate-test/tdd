package com.aihoshistar.sample.user.application.port.out

interface SaveUserFriendPersistencePort {
    fun save(userId: Long, friendUserId: Long)
}
