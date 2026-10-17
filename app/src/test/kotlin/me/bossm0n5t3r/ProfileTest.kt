package me.bossm0n5t3r

import kotlin.test.Test
import kotlin.test.assertEquals

class ProfileTest {
    @Test
    fun `vararg methods without arguments preserve all categories`() {
        val base = TechStack().stack("Kotlin", "Spring").practice("Testing", "TDD").withAI("LLM")

        assertEquals(base, base.practice())
        assertEquals(base, base.withAI())
    }

    @Test
    fun `additions accumulate in order without mutating the base or sibling branches`() {
        val base = TechStack().stack("Kotlin", "Spring").practice("Testing").withAI("LLM")

        val backend =
            base.stack("Java", "Quarkus").practice("TDD", "Refactoring").withAI("RAG", "Agents")
        val tooling = base.stack("Kotlin", "Gradle").practice("Linting").withAI("Agents")

        assertEquals(
            TechStack(
                stack = listOf("Kotlin" to "Spring"),
                practice = listOf("Testing"),
                ai = listOf("LLM"),
            ),
            base,
        )
        assertEquals(
            TechStack(
                stack = listOf("Kotlin" to "Spring", "Java" to "Quarkus"),
                practice = listOf("Testing", "TDD", "Refactoring"),
                ai = listOf("LLM", "RAG", "Agents"),
            ),
            backend,
        )
        assertEquals(
            TechStack(
                stack = listOf("Kotlin" to "Spring", "Kotlin" to "Gradle"),
                practice = listOf("Testing", "Linting"),
                ai = listOf("LLM", "Agents"),
            ),
            tooling,
        )
    }
}
