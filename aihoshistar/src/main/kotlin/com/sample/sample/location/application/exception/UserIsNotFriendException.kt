package com.aihoshistar.sample.location.application.exception

import com.aihoshistar.sample.common.exception.CustomException
import org.springframework.http.HttpStatus

class UserIsNotFriendException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    errorCode = "LOCATION__USER_IS_NOT_A_FRIEND",
    message = "user is not a friend",
)
