package com.aihoshistar.sample.location.application.service

import com.aihoshistar.sample.location.application.port.`in`.SaveUserLocationHistoryCommand
import com.aihoshistar.sample.location.application.port.`in`.SaveUserLocationHistoryUseCase
import com.aihoshistar.sample.location.application.port.out.SaveLocationHistoryPersistencePort
import com.aihoshistar.sample.location.domain.model.UserLocationHistory
import org.springframework.stereotype.Service

@Service
class SaveUserLocationHistoryService(
    private val persistencePort: SaveLocationHistoryPersistencePort,
) : SaveUserLocationHistoryUseCase {
    override fun execute(command: SaveUserLocationHistoryCommand) {
        val history = command.let {
            UserLocationHistory(
                userId = it.userId,
                location = it.location,
            )
        }
        persistencePort.save(history = history)
    }
}
