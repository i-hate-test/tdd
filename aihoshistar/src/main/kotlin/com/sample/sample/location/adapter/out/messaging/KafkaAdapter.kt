package com.aihoshistar.sample.location.adapter.out.messaging

import com.aihoshistar.sample.location.application.port.out.MessagingPort
import com.aihoshistar.sample.location.domain.model.UpdateUserLocation
import org.springframework.stereotype.Component

@Component
class KafkaAdapter(
    private val updateLocationProducer: UpdateLocationProducer,
) : MessagingPort {
    override fun sendLocationUpdated(message: UpdateUserLocation) {
        updateLocationProducer.send(key = message.userId.toString(), message = message)
    }
}
