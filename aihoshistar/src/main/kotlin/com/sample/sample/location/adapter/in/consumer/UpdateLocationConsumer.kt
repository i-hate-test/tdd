package com.aihoshistar.sample.location.adapter.`in`.consumer

import com.aihoshistar.sample.location.application.port.`in`.SaveUserLocationHistoryCommand
import com.aihoshistar.sample.location.application.port.`in`.SaveUserLocationHistoryUseCase
import com.aihoshistar.sample.location.application.port.`in`.UpdateLocationCommand
import com.aihoshistar.sample.location.application.port.`in`.UpdateLocationUseCase
import com.aihoshistar.sample.location.domain.model.UpdateUserLocation
import com.aihoshistar.sample.location.domain.vo.UserLocation
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class UpdateLocationConsumer(
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val saveUserLocationHistoryUseCase: SaveUserLocationHistoryUseCase,
) {
    @KafkaListener(
        topics = ["\${spring.kafka.topic.location-updated}"],
        groupId = "hide",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun listen(message: UpdateUserLocation) {
        logger.info { "Received: $message" }
        val updateLocationCommand = message.let {
            UpdateLocationCommand(
                userId = it.userId,
                lat = it.lat,
                lng = it.lng,
            )
        }
        updateLocationUseCase.execute(command = updateLocationCommand)

        val saveHistoryCommand = message.let {
            SaveUserLocationHistoryCommand(
                userId = it.userId,
                location = UserLocation(
                    lat = it.lat, lng = it.lng
                ),
            )
        }
        saveUserLocationHistoryUseCase.execute(command = saveHistoryCommand)
    }
}
