package com.rest.demo.utils.exception

import com.rest.demo.utils.Response
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
        val errorDetails = Response( exception.status.value(), exception.message)
        return ResponseEntity<Any>(errorDetails, HttpStatus.NOT_FOUND)
    }

    // handling global exception
    @ExceptionHandler(Exception::class)
    fun globalExceptionHandling(exception: Exception, request: WebRequest): ResponseEntity<*>? {
        val errorDetails = Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
        return ResponseEntity<Any>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationError(exception: MethodArgumentNotValidException): ResponseEntity<*> {
        val error = Response(
            HttpStatus.BAD_REQUEST.value(),
            exception.bindingResult.getFieldError()?.defaultMessage ?: "validation error"
        )
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}
