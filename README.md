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
        blog = "https://bossm0n5t3r.github.io/"
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
    %{blog: "https://bossm0n5t3r.github.io/"}
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

### Latest posts of [@bossm0n5t3r](https://github.com/bossm0n5t3r)

<!-- BLOG-POST-LIST:START -->
- [Phoenix + LiveView 전역 Rate Limit 적용기](https://bossm0n5t3r.github.io/posts/phoenix-liveview-rate-limit-ets/)
- [🔍 방문 기록 검색 확장 프로그램, Historikie](https://bossm0n5t3r.github.io/projects/historikie/)
- [Kotlin 멤버 함수와 확장 함수의 동작 방식 차이 및 내부 원리](https://bossm0n5t3r.github.io/posts/kotlin-extension-vs-member-function/)
- [[그림과 실습으로 배우는 쿠버네티스 입문] 12장. 이 책 이후의 학습에 대하여](https://bossm0n5t3r.github.io/books/bbf-k8s-chapter-12/)
- [[그림과 실습으로 배우는 쿠버네티스 입문] 11장. 옵저버빌리티와 모니터링 다루기](https://bossm0n5t3r.github.io/books/bbf-k8s-chapter-11/)
<!-- BLOG-POST-LIST:END -->

---

![](https://raw.githubusercontent.com/bossm0n5t3r/bossm0n5t3r/output/github-snake.svg)
[![GitHub Streak](https://github-readme-streak-stats-bossm0n5t3r.vercel.app?user=bossm0n5t3r&theme=catppuccin-mocha)](https://git.io/streak-stats)
![](https://leetcard.jacoblin.cool/bossm0n5t3r)
![](https://projecteuler.net/profile/bossm0n5t3r.png)
