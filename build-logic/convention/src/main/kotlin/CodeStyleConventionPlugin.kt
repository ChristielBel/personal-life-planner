import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

class CodeStyleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

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

            val detekt = extensions.getByType<DetektExtension>()

            extensions.configure<DetektExtension> {
                buildUponDefaultConfig = true
                parallel = true
                autoCorrect = true
                config.setFrom(rootProject.file("config/detekt/detekt.yml"))
                baseline = file("detekt-baseline.xml")
                source.setFrom("src/main/kotlin", "src/main/java")
            }

            dependencies.add(
                "detektPlugins",
                "io.gitlab.arturbosch.detekt:detekt-formatting:${detekt.toolVersion}"
            )
        }
    }
}