package com.rest.demo.utils.exception

import com.rest.demo.utils.CustomeResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    // handling specific exception
    @ExceptionHandler(CustomException::class)
    fun resourceNotFoundHandling(exception: CustomException, request: WebRequest): ResponseEntity<*>? {
        val errorDetails = CustomeResponse(exception.status.value(), exception.message, exception.message)
        return ResponseEntity<Any>(errorDetails, HttpStatus.NOT_FOUND)
    }

    // handling global exception
    @ExceptionHandler(Exception::class)
    fun globalExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        val errorDetails = CustomeResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message, exception.message)
        return ResponseEntity<Any>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    //handling for value not valid
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationError(exception: MethodArgumentNotValidException): ResponseEntity<Any> {
        return ResponseEntity(
            CustomeResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.bindingResult.getFieldError()?.defaultMessage ?: "validation error",
                exception.bindingResult.getFieldError()?.defaultMessage
            ), HttpStatus.BAD_REQUEST
        )
    }

    //handling on unauthorized
    @ExceptionHandler(value = [UnAuthorizedException::class])
    fun unAuthorizedRequest(unAuthorizedException: UnAuthorizedException): ResponseEntity<Any> {
        val error = CustomeResponse(
            HttpStatus.UNAUTHORIZED.value(),
            "Unauthorized",
            "Please put api-key"
        )
        return ResponseEntity(error, HttpStatus.UNAUTHORIZED)
    }
}
