package com.aihoshistar.sample.user.application.port.out

import com.aihoshistar.sample.user.domain.vo.Location
import com.aihoshistar.sample.user.domain.vo.UserStatus

interface UpdateUserPersistencePort {
    fun updateLocationById(userId: Long, location: Location): Long

    fun updateStatusById(userId: Long, status: UserStatus): Long
}
