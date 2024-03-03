package com.aihoshistar.sample.location.application.port.`in`

import com.aihoshistar.sample.location.domain.vo.UserLocation

data class SaveUserLocationHistoryCommand(
    val userId: Long,
    val location: UserLocation,
)
interface SaveUserLocationHistoryUseCase {
    fun execute(command: SaveUserLocationHistoryCommand)
}
