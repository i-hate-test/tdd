package com.aihoshistar.sample.location.application.port.`in`

import com.aihoshistar.sample.user.domain.model.UserWithLocation

data class GetLocationQuery(val userId: Long, val friendUserId: Long)

interface GetLocationUseCase {
    fun execute(query: GetLocationQuery): UserWithLocation
}
