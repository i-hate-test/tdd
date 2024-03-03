package com.aihoshistar.sample.location.application.service

import com.aihoshistar.sample.location.application.port.`in`.UpdateLocationCommand
import com.aihoshistar.sample.location.application.port.`in`.UpdateLocationUseCase
import com.aihoshistar.sample.location.application.port.out.UserExternalPort
import com.aihoshistar.sample.location.domain.vo.UserLocation
import org.springframework.stereotype.Service

@Service
class UpdateLocationService(
    private val userExternalPort: UserExternalPort,
) : UpdateLocationUseCase {
    override fun execute(command: UpdateLocationCommand): Long {
        return userExternalPort.updateUserLocation(
            userId = command.userId, location = UserLocation(lat = command.lat, lng = command.lng)
        )
    }
}
