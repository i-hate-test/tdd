package com.aihoshistar.sample.user.application.exception

import com.aihoshistar.sample.common.exception.CustomException
import org.springframework.http.HttpStatus

class UserAlreadyExistException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST, errorCode = "USER__ALREADY_EXIST", message = "user already exist"
)
