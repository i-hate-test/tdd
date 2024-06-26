package com.aihoshistar.sample.location.application.service

import com.aihoshistar.sample.location.application.exception.GhostModeUserException
import com.aihoshistar.sample.location.application.exception.UserIsNotFriendException
import com.aihoshistar.sample.location.application.port.`in`.GetLocationQuery
import com.aihoshistar.sample.location.application.port.`in`.GetLocationUseCase
import com.aihoshistar.sample.location.application.port.out.UserExternalPort
import com.aihoshistar.sample.user.domain.model.UserWithLocation
import com.aihoshistar.sample.user.domain.vo.UserStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetLocationService(
    private val userExternalPort: UserExternalPort,
) : GetLocationUseCase {
    override fun execute(query: GetLocationQuery): UserWithLocation {
        val userId = query.userId
        val friendUserId = query.friendUserId

        val isFriend = userExternalPort.isFriendWith(userId = userId, friendUserId = friendUserId)
        if (!isFriend) {
            throw UserIsNotFriendException()
        }

        val user = userExternalPort.getUser(userId = userId)
        if (user.status == UserStatus.GHOST) {
            throw GhostModeUserException()
        }
        return user.let {
            UserWithLocation(
                userId = it.id,
                nickname = it.nickname,
                location = it.location,
                stayedAt = it.stayedAt,
            )
        }
    }
}
