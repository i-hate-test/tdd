package com.aihoshistar.sample.location.application.port.out

import com.aihoshistar.sample.location.domain.model.UserLocationHistory

interface SaveLocationHistoryPersistencePort {
    fun save(history: UserLocationHistory)
}
