package com.aihoshistar.sample.user.application.port.`in`

import com.aihoshistar.sample.user.domain.model.UserWithLocation

data class GetFriendLocationsQuery(val userId: Long)

interface GetFriendLocationsUseCase {
    fun execute(query: GetFriendLocationsQuery): List<UserWithLocation>
}
