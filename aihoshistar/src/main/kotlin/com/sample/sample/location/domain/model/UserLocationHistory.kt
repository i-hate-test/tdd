package com.aihoshistar.sample.location.domain.model

import com.aihoshistar.sample.location.domain.vo.UserLocation
import org.bson.types.ObjectId

class UserLocationHistory(
    val id: ObjectId = ObjectId(),
    val userId: Long,
    val location: UserLocation,
)
