package com.aihoshistar.sample.common.security

import org.springframework.security.core.AuthenticationException

class JwtAuthenticationFailException : AuthenticationException("AUTHENTICATION_ERROR")
