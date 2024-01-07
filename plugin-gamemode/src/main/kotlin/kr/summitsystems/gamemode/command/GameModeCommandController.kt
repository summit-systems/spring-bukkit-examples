package kr.summitsystems.gamemode.command

import kr.summitsystems.springbukkit.core.checker.annotation.EnsuresMainThread
import kr.summitsystems.springbukkit.command.CommandContext
import kr.summitsystems.springbukkit.command.annotation.CommandController
import kr.summitsystems.springbukkit.command.annotation.CommandMapping
import org.bukkit.GameMode
import org.bukkit.entity.Player

@CommandController
@CommandMapping(command = "gamemode") // Optional declaration when only one command exists.
class GameModeCommandController {

    @EnsuresMainThread
    @CommandMapping
    fun CommandContext.gameMode(
        gameMode: GameMode
    ) {
        val sender = sender
        if (sender !is Player) {
            sender.sendMessage("This command is only available for players.")
            return
        }
        sender.gameMode = gameMode
    }
}