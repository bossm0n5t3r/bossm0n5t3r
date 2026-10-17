package me.bossm0n5t3r

enum class Role {
    BACKEND
}

data class Developer(
    val name: String,
    val major: String,
    val role: Role,
    val techStack: TechStack,
    val links: Map<String, String>,
)

class DeveloperBuilder {
    lateinit var role: Role
    var techStack: TechStack = TechStack()
    var links: Map<String, String> = emptyMap()

    fun build(name: String, major: String): Developer =
        Developer(
            name = name,
            major = major,
            role = role,
            techStack = techStack,
            links = links,
        )

    fun stack(language: String, framework: String): TechStack =
        TechStack(stack = listOf(language to framework))
}

data class TechStack(
    val stack: List<Pair<String, String>> = emptyList(),
    val practice: List<String> = emptyList(),
    val ai: List<String> = emptyList(),
) {
    fun stack(language: String, framework: String) = copy(stack = stack + (language to framework))

    fun practice(vararg skills: String) = copy(practice = practice + skills)

    fun withAI(vararg topics: String) = copy(ai = ai + topics)
}

fun developer(
    name: String,
    major: String,
    configure: DeveloperBuilder.() -> Unit,
): Developer = DeveloperBuilder().apply(configure).build(name, major)

val bossm0n5t3r: Developer =
    developer(
        name = "Ji-Hoon Kim",
        major = "Mathematics",
    ) {
        role = Role.BACKEND

        techStack =
            stack(
                    language = "Kotlin",
                    framework = "Spring",
                )
                .practice("Testing", "TDD", "Refactoring")
                .withAI("LLM", "Local-first AI")

        links = mapOf("Blog" to "https://blog.m0n5t3r.com/")
    }
