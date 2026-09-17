## About me 🧑‍💻

```kotlin
val bossm0n5t3r = developer {
    about {
        name = "Ji-Hoon Kim"
        role = Backend
    }
    tech(
        "Kotlin",
        "Spring",
        "Testing",
    )
    links {
        blog = "https://blog.m0n5t3r.com/"
    }
}
```

```elixir
defmodule Developer do
  @spec about() :: %{name: String.t(), role: atom()}
  def about do
    %{name: "Ji-Hoon Kim", role: :backend}
  end

  @spec tech() :: [String.t()]
  def tech do
    ["Elixir", "Phoenix"]
  end

  @spec links() :: %{blog: String.t()}
  def links do
    %{blog: "https://blog.m0n5t3r.com/"}
  end

  @spec profile() :: %{about: map(), tech: [String.t()], links: map()}
  def profile do
    %{about: about(), tech: tech(), links: links()}
  end
end

bossm0n5t3r = Developer.profile()
```

---

### GPG

- Fingerprint: `033C F3F9 C1D5 D548 4930  A8CB 682D 5D1E 6D45 BD93`
- Public key
  - [GitHub](https://github.com/bossm0n5t3r.gpg)
  - [keys.openpgp.org](https://keys.openpgp.org/vks/v1/by-fingerprint/033CF3F9C1D5D5484930A8CB682D5D1E6D45BD93)

---

### Latest posts

<!-- BLOG-POST-LIST:START -->
<!-- BLOG-POST-LIST:END -->

---

![](./assets/github-metrics.svg)
![](./assets/github-snake.svg)
![](https://github-readme-streak-stats-bossm0n5t3r.vercel.app?user=bossm0n5t3r&theme=catppuccin-mocha)
![](https://leetcard.jacoblin.cool/bossm0n5t3r)
![](https://projecteuler.net/profile/bossm0n5t3r.png)
