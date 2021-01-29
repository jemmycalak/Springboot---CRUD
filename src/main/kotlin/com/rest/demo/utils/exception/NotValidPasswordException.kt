package com.rest.demo.utils.exception

import javax.validation.ValidationException

class NotValidPasswordException(message: String?) : ValidationException(message)
