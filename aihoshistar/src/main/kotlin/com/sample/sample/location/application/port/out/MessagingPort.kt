package com.aihoshistar.sample.location.application.port.out

import com.aihoshistar.sample.location.domain.model.UpdateUserLocation

interface MessagingPort {
    fun sendLocationUpdated(message: UpdateUserLocation)
}
