package me.bossm0n5t3r

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption.APPEND
import java.nio.file.StandardOpenOption.CREATE
import java.time.Clock
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val SEOUL = ZoneId.of("Asia/Seoul")
private val TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssxxx")

internal fun computeCommitTimestamp(
    eventName: String,
    githubSha: String? = null,
    clock: Clock = Clock.systemUTC(),
): String {
    if (eventName == "push") {
        val sha = requireNotNull(githubSha) { "GITHUB_SHA is required for push events" }
        val process =
            ProcessBuilder("git", "show", "-s", "--format=%cI", sha)
                .redirectErrorStream(true)
                .start()
        val output = process.inputStream.bufferedReader().use { it.readText() }.trim()
        check(process.waitFor() == 0) { "git show failed: $output" }
        return output
    }

    val timestamp =
        if (eventName == "schedule") {
            val now = ZonedDateTime.now(clock.withZone(ZoneOffset.UTC))
            now.withHour(now.hour - now.hour % 3)
                .withMinute(0)
                .withSecond(0)
                .withNano(0)
                .withZoneSameInstant(SEOUL)
        } else {
            val now = ZonedDateTime.now(clock.withZone(SEOUL))
            now.plusMinutes((15 - now.minute % 15).toLong()).withSecond(0).withNano(0)
        }
    return timestamp.format(TIMESTAMP_FORMAT)
}

internal fun writeGithubOutput(path: Path, value: String) {
    Files.writeString(path, "value=$value\n", CREATE, APPEND)
}

/**
 * Run with `./gradlew --quiet --console=plain :app:run` on JDK 25.
 *
 * Reads GITHUB_EVENT_NAME and appends `value=<timestamp>` to GITHUB_OUTPUT. Push events require
 * GITHUB_SHA and preserve the Git committer timestamp. Scheduled events floor UTC to a three-hour
 * slot; all other events advance to the strictly next quarter-hour in Asia/Seoul.
 */
fun main() {
    val environment = System.getenv()
    val value =
        computeCommitTimestamp(
            eventName = environment.getValue("GITHUB_EVENT_NAME"),
            githubSha = environment["GITHUB_SHA"],
        )
    writeGithubOutput(Path.of(environment.getValue("GITHUB_OUTPUT")), value)
}
