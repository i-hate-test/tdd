package com.aihoshistar.sample.user.application.port.`in`

import com.aihoshistar.sample.user.domain.model.UserWithLocation

data class GetUserLocationQuery(val userId: Long)

interface GetUserLocationUseCase {
    fun execute(query: GetUserLocationQuery): UserWithLocation
}
