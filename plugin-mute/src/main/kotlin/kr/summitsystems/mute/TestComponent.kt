package kr.summitsystems.mute

import jakarta.annotation.PostConstruct
import kr.summitsystems.springbukkit.command.CommandExecutor
import kr.summitsystems.springbukkit.core.util.extension.print
import org.springframework.stereotype.Component

@Component
class TestComponent(private val executor: CommandExecutor) {
    @PostConstruct
    fun postConstruct() {
        executor::class.simpleName.print("executor : ")
    }
}