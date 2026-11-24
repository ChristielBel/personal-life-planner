import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.UntrackedTask
import org.gradle.api.tasks.options.Option

@UntrackedTask(because = "Rewrites the build file in place")
abstract class SortDependenciesTask : DefaultTask() {

    @get:Internal
    abstract val buildFile: RegularFileProperty

    @get:Input
    @get:Option(option = "check", description = "Only verify order, do not modify the file")
    abstract val checkOnly: Property<Boolean>

    private data class Entry(
        val lines: List<String>,
        val configRank: Int,
        val kindWeight: Int,
        val key: String,
    )

    @TaskAction
    fun sort() {
        val file = buildFile.get().asFile
        if (!file.exists()) return

        val text = file.readText().replace("\r\n", "\n")

        val match = Regex("(?m)^dependencies\\s*\\{").find(text) ?: return
        val bodyStart = match.range.last + 1

        var depth = 1
        var i = bodyStart
        while (i < text.length && depth > 0) {
            when (text[i]) {
                '{' -> depth++
                '}' -> depth--
            }
            i++
        }
        val bodyEnd = i - 1

        val blocks = parseBlocks(text.substring(bodyStart, bodyEnd).lines())
        val rendered = blocks.joinToString("\n\n") { block ->
            block
                .sortedWith(compareBy({ it.configRank }, { it.kindWeight }, { it.key }))
                .joinToString("\n") { it.lines.joinToString("\n") }
        }

        val result = text.substring(0, bodyStart) + "\n" + rendered + "\n" + text.substring(bodyEnd)

        if (result == text) return

        if (checkOnly.getOrElse(false)) {
            throw GradleException(
                "Dependencies in ${file.path} are not sorted. Run ./gradlew ${path.replace("check", "sort").replace("Sorted", "")}"
            )
        }
        file.writeText(result)
    }

    private fun parseBlocks(body: List<String>): List<List<Entry>> {
        val blocks = mutableListOf<List<Entry>>()
        var current = mutableListOf<Entry>()
        var comments = mutableListOf<String>()
        var statement = mutableListOf<String>()
        var balance = 0

        for (raw in body) {
            val line = raw.trimEnd()
            val t = line.trim()

            if (statement.isEmpty()) {
                if (t.isEmpty()) {
                    if (current.isNotEmpty()) {
                        blocks += current
                        current = mutableListOf()
                    }
                    continue
                }
                if (t.startsWith("//")) {
                    comments += line
                    continue
                }
            }

            statement += line
            balance += t.count { it == '(' || it == '{' } - t.count { it == ')' || it == '}' }

            if (balance <= 0) {
                balance = 0
                current += toEntry(comments + statement, statement.first().trim())
                comments = mutableListOf()
                statement = mutableListOf()
            }
        }

        // хвостовые комментарии или незакрытая запись — оставляем как есть, в конец блока
        val leftovers = comments + statement
        if (leftovers.isNotEmpty()) {
            current += Entry(leftovers, Int.MAX_VALUE, Int.MAX_VALUE, "")
        }
        if (current.isNotEmpty()) blocks += current
        return blocks
    }

    private fun toEntry(lines: List<String>, first: String): Entry {
        val config = first.substringBefore("(").trim()
        val arg = first.substringAfter("(", "").trim()
        val weight = when {
            arg.startsWith("project(") -> 0
            arg.startsWith("platform(") || arg.startsWith("enforcedPlatform(") -> 1
            else -> 2
        }
        val rank = CONFIG_ORDER.indexOf(config).let { if (it == -1) CONFIG_ORDER.size else it }
        return Entry(lines, rank, weight, arg.substringBefore(")"))
    }

    private companion object {
        val CONFIG_ORDER = listOf(
            "api", "implementation", "compileOnly", "runtimeOnly",
            "debugImplementation",
            "ksp", "kapt",
            "testImplementation", "androidTestImplementation",
        )
    }
}
