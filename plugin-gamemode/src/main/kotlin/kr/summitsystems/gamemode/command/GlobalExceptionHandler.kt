package kr.summitsystems.gamemode.command

import kr.summitsystems.springbukkit.command.CommandContext
import kr.summitsystems.springbukkit.command.annotation.CommandControllerAdvice
import kr.summitsystems.springbukkit.command.annotation.ExceptionHandler
import org.bukkit.GameMode
import org.springframework.core.convert.ConversionFailedException

@CommandControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler
    fun CommandContext.handleConversionFailedException(exception: ConversionFailedException) {
        if (exception.targetType.type == GameMode::class.java) {
            sender.sendMessage("§cPlease specify the available game mode. (${GameMode.values().joinToString(", ")})")
        } else {
            sender.sendMessage("§cConversion Failed: ${exception.targetType.name}")
        }
    }
}