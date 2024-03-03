package com.aihoshistar.sample.user.domain.model

import com.aihoshistar.sample.user.domain.vo.Location
import com.aihoshistar.sample.user.domain.vo.UserStatus
import java.time.LocalDateTime

class User(
    val password: String,
    val email: String,
    val nickname: String,
    val status: UserStatus,
    val location: Location,
    val stayedAt: LocalDateTime,
    val id: Long = 0L,
)
