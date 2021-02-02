package com.rest.demo.utils.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus
class CustomException(override val message: String, var status: HttpStatus = HttpStatus.NOT_FOUND): RuntimeException()
