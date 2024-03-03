package com.aihoshistar.sample.user.adapter.`in`.v1

import com.aihoshistar.sample.common.response.ApiResponse
import com.aihoshistar.sample.common.security.CurrentUser
import com.aihoshistar.sample.user.application.port.`in`.AddFriendCommand
import com.aihoshistar.sample.user.application.port.`in`.AddFriendUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class AddFriendRequest(val friendUserId: Long)

@RestController
@RequestMapping("/api/v1/user/friend")
class AddFriendV1Controller(
    private val useCase: AddFriendUseCase,
) {
    @PostMapping("")
    fun addFriend(
        @RequestBody @Valid body: AddFriendRequest,
        @AuthenticationPrincipal currentUser: CurrentUser,
    ): ApiResponse<Void> {
        val command = AddFriendCommand(
            userId = currentUser.id, friendUserId = body.friendUserId
        )
        useCase.execute(command = command)
        return ApiResponse.success(statusCode = HttpStatus.OK)
    }
}
