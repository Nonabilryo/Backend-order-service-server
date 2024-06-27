package nonabili.orderserviceserver.controller

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import nonabili.orderserviceserver.util.ResponseFormat
import nonabili.orderserviceserver.util.ResponseFormatBuilder
import nonabili.orderserviceserver.util.error.CustomError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {
    val log = LoggerFactory.getLogger(javaClass)
    @ExceptionHandler(CustomError::class)
    fun handleCustomError(e: CustomError): ResponseEntity<ResponseFormat<Any>> {
        return ResponseEntity
            .status(e.reason.status.value())
            .body(ResponseFormatBuilder {
                state = e.reason.status.value()
                message = e.reason.message
            }.noData())
    }
    // server error
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ResponseFormat<Any>> {
        log.error("error:", e)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseFormatBuilder {
                state = HttpStatus.INTERNAL_SERVER_ERROR.value()
                message = e.message
            }.noData())
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleException(e: HttpRequestMethodNotSupportedException): ResponseEntity<ResponseFormat<Any>> {
        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(ResponseFormatBuilder {
                state = HttpStatus.METHOD_NOT_ALLOWED.value()
                message = "${e.method} is not allowed this request (${e.supportedMethods?.joinToString(" ,") })"
            }.noData())
    }
    // json element check
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleException(e: HttpMessageNotReadableException): ResponseEntity<ResponseFormat<Any>> {
        val cause = e.cause
        val result = if (cause is MissingKotlinParameterException) "missing:" + cause.parameter.name else "missing parameter"
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseFormatBuilder {
                state = HttpStatus.BAD_REQUEST.value()
                message = "${result}"
            }.noData())
    }
    // valid check
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<ResponseFormat<Any>> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseFormatBuilder {
                state = HttpStatus.BAD_REQUEST.value()
                message = "${e.bindingResult.fieldError?.defaultMessage}"
            }.noData())
    }
}