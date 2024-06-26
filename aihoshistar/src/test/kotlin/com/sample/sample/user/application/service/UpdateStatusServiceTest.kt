package com.aihoshistar.sample.user.application.service

import com.aihoshistar.sample.user.application.port.`in`.UpdateStatusCommand
import com.aihoshistar.sample.user.application.port.out.UpdateUserPersistencePort
import com.aihoshistar.sample.user.domain.vo.UserStatus
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UpdateStatusServiceTest : BehaviorSpec({
    val updateUserPersistencePort = mockk<UpdateUserPersistencePort>()
    val updateStatusService = UpdateStatusService(updateUserPersistencePort = updateUserPersistencePort)

    Given("유저의") {
        val command = UpdateStatusCommand(userId = 1L, status = UserStatus.GHOST)
        every { updateUserPersistencePort.updateStatusById(any(), any()) } returns 1L

        When("상태 업데이트를 요청하면") {
            updateStatusService.execute(command = command)

            Then("상태가 변경된다") {
                verify(exactly = 1) { updateUserPersistencePort.updateStatusById(userId = command.userId, status = command.status) }
            }
        }
    }
})
