package com.aihoshistar.sample.common.util.jwt

import com.aihoshistar.sample.common.exception.CustomException
import org.springframework.http.HttpStatus

class DecodeTokenException : CustomException(
    statusCode = HttpStatus.UNAUTHORIZED,
    errorCode = "AUTH__INVALID_TOKEN",
    message = ""
)
