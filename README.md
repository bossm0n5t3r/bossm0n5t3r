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
- [&quot;그로스 해킹: 데이터로 증명하는 성장의 공식&quot;을 읽고](https://blog.m0n5t3r.com/books/growth-hacking/)
- [KotlinLLM Playground 만들기: asLlm&lpar;&rpar;과 mockLlm&lpar;&rpar; 실험해보기](https://blog.m0n5t3r.com/posts/kotlinllm-playground/)
- [나는 AI를 어떻게 쓰고 있는가](https://blog.m0n5t3r.com/posts/how-i-use-ai/)
- [readability4k: Kotlin 으로 옮긴 Mozilla Readability](https://blog.m0n5t3r.com/projects/readability4k/)
- [Git 커밋 메시지, 이제 diff만 보고 AI가 작성합니다: ACW 개발기](https://blog.m0n5t3r.com/projects/acw/)
<!-- BLOG-POST-LIST:END -->

---

![](./assets/github-snake.svg)
![](https://github-readme-streak-stats-bossm0n5t3r.vercel.app?user=bossm0n5t3r&theme=catppuccin-mocha)
![](https://leetcard.jacoblin.cool/bossm0n5t3r)
![](https://projecteuler.net/profile/bossm0n5t3r.png)
