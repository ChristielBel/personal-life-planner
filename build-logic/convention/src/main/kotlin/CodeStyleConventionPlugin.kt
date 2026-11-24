import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class CodeStyleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val moduleBuildFile = layout.projectDirectory.file("build.gradle.kts")

            val sort = tasks.register<SortDependenciesTask>("sortDependencies") {
                group = "code style"
                description = "Sorts the dependencies block in build.gradle.kts"
                buildFile.set(moduleBuildFile)
                checkOnly.convention(false)
            }

            val check = tasks.register<SortDependenciesTask>("checkDependenciesSorted") {
                group = "code style"
                description = "Fails if the dependencies block is not sorted"
                buildFile.set(moduleBuildFile)
                checkOnly.set(true)
            }

            val codeStyle = tasks.register("codeStyle") {
                group = "code style"
                description = "Applies all code style tasks"
                dependsOn(sort)
            }

            pluginManager.withPlugin("com.diffplug.spotless") {
                codeStyle.configure { dependsOn("spotlessApply") }
            }

            tasks.matching { it.name == "check" }.configureEach {
                dependsOn(check)
            }
        }
    }
}