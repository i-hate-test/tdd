package com.aihoshistar.sample.location.domain.model

import com.aihoshistar.sample.location.domain.vo.LocationRequestType

data class RequestFriendLocation(val type: LocationRequestType, val userId: Long)
