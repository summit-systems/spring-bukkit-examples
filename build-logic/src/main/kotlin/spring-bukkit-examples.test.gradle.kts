import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.*

plugins {
    java
}

val bukkitFileName = "paper.jar"
val testBukkitEnvDir = project.gradle.rootProject.projectDir.toString() + "/test-bukkit"

val executeTask by tasks.registering {
    dependsOn(copyTask)
    doLast {
        project.javaexec {
            @Suppress("DEPRECATION")
            this.setMain("-jar")
            this.workingDir = file(testBukkitEnvDir)
            this.args("${testBukkitEnvDir}/${bukkitFileName}")
        }
    }
}

val copyTask by tasks.registering {
    doLast {
        if (file(testBukkitEnvDir).exists()) {
            copy {
                this.from(tasks.withType<Jar>().getByName("jar").destinationDirectory.get().toString())
                this.into("$testBukkitEnvDir/plugins")
            }
        }
    }
}

tasks.withType<Jar> {
    finalizedBy(executeTask)
}