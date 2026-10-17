package me.bossm0n5t3r

import java.nio.file.Files
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CommitTimestampTest {
    private fun clock(instant: String) = Clock.fixed(Instant.parse(instant), ZoneOffset.UTC)

    @Test
    fun `schedule floors UTC to three hour slots and formats Seoul time`() {
        val cases =
            mapOf(
                "2026-10-17T00:00:00Z" to "2026-10-17T09:00:00+09:00",
                "2026-10-17T02:59:59.999Z" to "2026-10-17T09:00:00+09:00",
                "2026-10-17T03:00:00Z" to "2026-10-17T12:00:00+09:00",
                "2026-10-17T23:59:59Z" to "2026-10-18T06:00:00+09:00",
            )
        for ((instant, expected) in cases) {
            assertEquals(
                expected,
                computeCommitTimestamp("schedule", clock = clock(instant)),
                instant,
            )
        }
    }

    @Test
    fun `manual and other events advance to a strictly later quarter hour`() {
        val cases =
            mapOf(
                "2026-10-17T00:00:00Z" to "2026-10-17T09:15:00+09:00",
                "2026-10-17T00:14:59.999Z" to "2026-10-17T09:15:00+09:00",
                "2026-10-17T00:15:00Z" to "2026-10-17T09:30:00+09:00",
                "2026-12-31T14:59:59Z" to "2027-01-01T00:00:00+09:00",
            )
        for (event in listOf("workflow_dispatch", "other")) {
            for ((instant, expected) in cases) {
                assertEquals(
                    expected,
                    computeCommitTimestamp(event, clock = clock(instant)),
                    "$event: $instant",
                )
            }
        }
    }

    @Test
    fun `push requires a commit SHA`() {
        assertFailsWith<IllegalArgumentException> { computeCommitTimestamp("push") }
    }

    @Test
    fun `GitHub output creates the file and appends without overwriting other outputs`() {
        val directory = Files.createTempDirectory("commit-timestamp-test")
        val output = directory.resolve("github-output")
        try {
            writeGithubOutput(output, "2026-10-17T09:00:00+09:00")
            writeGithubOutput(output, "2026-10-17T09:15:00+09:00")
            assertEquals(
                "value=2026-10-17T09:00:00+09:00\nvalue=2026-10-17T09:15:00+09:00\n",
                Files.readString(output),
            )
        } finally {
            Files.deleteIfExists(output)
            Files.delete(directory)
        }
    }
}
