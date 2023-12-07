package kr.summitsystems.mute.presentation.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kr.summitsystems.mute.domain.service.MuteService
import kr.summitsystems.springbukkit.checker.annotation.EnsuresAsyncThread
import kr.summitsystems.springbukkit.command.CommandContext
import kr.summitsystems.springbukkit.command.annotation.CommandArgument
import kr.summitsystems.springbukkit.command.annotation.CommandAuthorize
import kr.summitsystems.springbukkit.command.annotation.CommandController
import kr.summitsystems.springbukkit.command.annotation.CommandMapping
import kr.summitsystems.springbukkit.util.BukkitColorUtils
import org.bukkit.entity.Player
import org.bukkit.plugin.PluginDescriptionFile
import org.springframework.scheduling.annotation.Async
import java.time.Duration
import java.util.concurrent.TimeUnit

@CommandController
@CommandMapping(command = "mute")
class MuteAdminCommandController(
    private val pluginDescriptionFile: PluginDescriptionFile,
    private val muteService: MuteService
) {
    @CommandMapping
    fun CommandContext.executeRoot() {
        sender.sendMessage(BukkitColorUtils.parse("<#FAED7D>Running §7${pluginDescriptionFile.name} <#FAED7D>plugin version §7${pluginDescriptionFile.version}<#FAED7D>."))
        sender.sendMessage(BukkitColorUtils.parse("<#FAED7D>Use §a/mute help <#FAED7D>to view available commands."))
    }

    @Async
    @EnsuresAsyncThread
    @CommandAuthorize(permission = "mute.admin")
    @CommandMapping(path = "help")
    fun CommandContext.executeHelp() {
        sender.sendMessage(BukkitColorUtils.parse("<#FAED7D>Running §7${pluginDescriptionFile.name} <#FAED7D>plugin version §7${pluginDescriptionFile.version}<#FAED7D>."))
        sendUsageAll()
    }

    @CommandAuthorize(permission = "mute.admin")
    @CommandMapping(path = "apply")
    suspend fun CommandContext.executeApplyCommand(
        @CommandArgument(label = "target", messageSource = "command.mute.apply.arguments.target") targetPlayer: Player,
        @CommandArgument(label = "time", messageSource = "command.mute.apply.arguments.time") durationAmount: Long,
        @CommandArgument(label = "time-unit", messageSource = "command.mute.apply.arguments.timeunit") timeUnit: TimeUnit = TimeUnit.SECONDS
    ) {
        coroutineScope {
            launch(Dispatchers.Default) {
                muteService.applyMute(targetPlayer.uniqueId, Duration.of(durationAmount, timeUnit.toChronoUnit()))
            }
        }
    }

    @CommandAuthorize(permission = "mute.admin")
    @CommandMapping(path = "list")
    suspend fun CommandContext.executeListCommand(
        @CommandArgument(label = "target", messageSource = "command.mute.apply.arguments.target") targetPlayer: Player
    ) {
        coroutineScope {
            launch(Dispatchers.Default) {
                val mutes = muteService.getUnexpiredMutes(targetPlayer.uniqueId)
                if (mutes.isEmpty()) {
                    sender.sendMessage(BukkitColorUtils.parse("<#FAED7D>Target player is not muted."))
                } else {
                    sender.sendMessage(BukkitColorUtils.parse("<#FAED7D>Mutes:"))
                    mutes.forEach {
                        sender.sendMessage(BukkitColorUtils.parse(" - expires: ${it.expiresAt}"))
                    }
                }
            }
        }
    }
}