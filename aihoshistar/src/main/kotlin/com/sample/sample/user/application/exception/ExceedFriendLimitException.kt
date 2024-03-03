package com.aihoshistar.sample.user.application.exception

import com.aihoshistar.sample.common.exception.CustomException
import org.springframework.http.HttpStatus

class ExceedFriendLimitException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST, errorCode = "USER__EXCEED_FRIEND_LIMIT", message = "exceed friend limit",
)
