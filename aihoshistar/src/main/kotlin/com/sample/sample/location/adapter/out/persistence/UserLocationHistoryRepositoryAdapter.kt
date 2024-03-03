package com.aihoshistar.sample.location.adapter.out.persistence

import com.aihoshistar.sample.location.adapter.out.persistence.mongo.UserLocationHistoryRepository
import com.aihoshistar.sample.location.application.port.out.SaveLocationHistoryPersistencePort
import com.aihoshistar.sample.location.domain.converter.UserLocationHistoryConverter
import com.aihoshistar.sample.location.domain.model.UserLocationHistory
import org.springframework.stereotype.Component

@Component
class UserLocationHistoryRepositoryAdapter(
    private val userLocationHistoryRepository: UserLocationHistoryRepository,
) : SaveLocationHistoryPersistencePort {
    override fun save(history: UserLocationHistory) {
        val historyEntity = UserLocationHistoryConverter.to(history)
        userLocationHistoryRepository.save(historyEntity)
    }
}
