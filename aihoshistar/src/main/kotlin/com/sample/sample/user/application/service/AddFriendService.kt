package com.aihoshistar.sample.user.application.service

import com.aihoshistar.sample.user.application.exception.AlreadyFriendException
import com.aihoshistar.sample.user.application.exception.ExceedFriendLimitException
import com.aihoshistar.sample.user.application.port.`in`.AddFriendCommand
import com.aihoshistar.sample.user.application.port.`in`.AddFriendUseCase
import com.aihoshistar.sample.user.application.port.out.GetUserFriendPersistencePort
import com.aihoshistar.sample.user.application.port.out.SaveUserFriendPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddFriendService(
    private val getUserFriendPersistencePort: GetUserFriendPersistencePort,
    private val saveUserFriendPersistencePort: SaveUserFriendPersistencePort,
) : AddFriendUseCase {
    companion object {
        private const val FRIEND_COUNT_LIMIT = 20
    }

    override fun execute(command: AddFriendCommand) {
        val (userId, friendUserId) = command
        val isExist = getUserFriendPersistencePort.existsByUserIdAndFriendUserId(
            userId = userId, friendUserId = friendUserId,
        )
        if (isExist) {
            throw AlreadyFriendException()
        }

        val canAdd = getUserFriendPersistencePort.countsByUserIdLessThan(
            userId = userId, count = FRIEND_COUNT_LIMIT,
        )
        if (!canAdd) {
            throw ExceedFriendLimitException()
        }

        saveUserFriendPersistencePort.save(userId = userId, friendUserId = friendUserId)
    }
}
