package com.rest.demo.utils

import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus
class CustomException(override val message: String): RuntimeException() {

}
