package com.aihoshistar.sample.user.application.port.`in`

import com.aihoshistar.sample.user.domain.vo.UserStatus

data class UpdateStatusCommand(val userId: Long, val status: UserStatus)

interface UpdateStatusUseCase {
    fun execute(command: UpdateStatusCommand)
}
