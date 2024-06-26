package com.aihoshistar.sample.location.application.port.out

import com.aihoshistar.sample.location.domain.vo.UserLocation
import com.aihoshistar.sample.user.domain.model.User
import com.aihoshistar.sample.user.domain.model.UserWithLocation

interface UserExternalPort {
    fun getFriendLocations(userId: Long): List<UserWithLocation>

    fun updateUserLocation(userId: Long, location: UserLocation): Long

    fun getUserLocation(userId: Long): UserWithLocation

    fun getUser(userId: Long): User

    fun isFriendWith(userId: Long, friendUserId: Long): Boolean
}
