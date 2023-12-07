package kr.summitsystems.mute.presentation

import kr.summitsystems.springbukkit.command.CommandContext
import kr.summitsystems.springbukkit.command.annotation.CommandControllerAdvice
import kr.summitsystems.springbukkit.command.annotation.ExceptionHandler
import org.springframework.core.annotation.Order
import org.springframework.core.convert.ConversionFailedException

@CommandControllerAdvice
class GlobalExceptionHandler {

    @Order(0)
    @ExceptionHandler
    fun CommandContext.handleException(throwable: Throwable) {
        throwable.printStackTrace()
        sender.sendMessage(throwable.message ?: "Unexpected error occurred.")
    }

    @Order(1)
    @ExceptionHandler
    fun CommandContext.handleConversionService(throwable: ConversionFailedException) {
        sender.sendMessage("conversion failed: ${throwable.targetType.name}")
    }
}