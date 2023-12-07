package kr.summitsystems.mute.infrastructure.translation

import jakarta.annotation.PostConstruct
import org.bukkit.plugin.Plugin
import org.springframework.context.annotation.Configuration
import java.io.File

@Configuration
class MessageSourceInitializer(private val plugin: Plugin) {
    @PostConstruct
    fun postConstruct() {
        if (findFile("messages.properties") == null) {
            loadFile("messages.properties")
            loadFile("messages_en.properties")
        }
    }

    private fun findFile(resourceName: String): File? {
        val file = File(plugin.dataFolder, resourceName)
        if (!file.exists()) {
            return null
        }
        return file
    }

    private fun loadFile(resourceName: String) {
        val inputStream = plugin.getResource(resourceName) ?: throw IllegalArgumentException("There is no resource with the name $resourceName found.")
        val file = File(plugin.dataFolder, resourceName)
        file.bufferedWriter().use { writer ->
            inputStream.reader().readLines().forEach {
                writer.appendLine(it)
            }
        }
    }
}