package com.aihoshistar.sample.location.domain.converter

import com.aihoshistar.sample.location.adapter.out.persistence.mongo.UserLocationHistoryEntity
import com.aihoshistar.sample.location.domain.model.UserLocationHistory

class UserLocationHistoryConverter private constructor() {
    companion object {
        fun from(locationHistory: UserLocationHistoryEntity): UserLocationHistory {
            with(locationHistory) {
                return UserLocationHistory(
                    id = id,
                    location = location,
                    userId = userId,
                )
            }
        }

        fun to(locationHistory: UserLocationHistory): UserLocationHistoryEntity {
            with(locationHistory) {
                return UserLocationHistoryEntity(
                    id = id,
                    location = location,
                    userId = userId,
                )
            }
        }
    }
}
