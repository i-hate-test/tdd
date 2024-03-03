package com.aihoshistar.sample.location.application.port.`in`

import com.aihoshistar.sample.user.domain.model.UserWithLocation

data class GetLocationsQuery(val userId: Long)
interface GetLocationsUseCase {
    fun execute(query: GetLocationsQuery): List<UserWithLocation>
}
