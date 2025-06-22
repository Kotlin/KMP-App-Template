This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop, Server.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - `iosMain` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
      you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.
    - `desktopMain` contains desktop applications that works on Windows, MacOS and Linux.
    - `wasmMain` contains web applications that works on Chrome (not tested on other browsers).

* `/server` is for the Ktor server application.

* `/shared` is for the code that will be shared between all targets in the project.

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

You can open the web application by running the Gradle task below:
```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

The KMP template tries to help you get started with these points:

- [ ] Core
    - [ ] Logging: [Napier](https://github.com/AAkira/Napier)?
      - [ ] Error reporting
      - [ ] Analytics
      - [ ] Tracing
    - [ ] Network: [ktor](https://ktor.io/)
    - [ ] Benchmarking
    - [ ] Build conventions
    - [ ] Flavours
    - [ ] Mocks
    - [ ] Test fixtures
    - [ ] Build info
    - [ ] Preferences
    - [ ] Storage
    - [ ] DI: koin?
    - [ ] Feature flags (local & remote)
    - [ ] Deep linking
    - [ ] Push notifications
    - [ ] TimeProvider
    - [ ] Local Formatters
    - [ ] Coroutine Dispatchers
    - [ ] Coroutine Dispatchers Test helper
    - [ ] Lint
    - [ ] Static code analysis
    - [ ] Unit testing
    - [ ] Test coverage: Jacoco?
    - [ ] Obfuscation & Shrinking
    - [ ] Pipelines
    - [ ] Releasing
    - [ ] Force updates
    - [ ] Dependency management: Renovate?

- [ ] UI
    - [ ] Design system
    - [ ] Gallery App
    - [ ] Navigation
    - [ ] Baseline profiles
    - [ ] Compose compiler metrics
    - [ ] Previews
    - [ ] Network image loading: coil
    - [ ] supportsDynamicTheming
    - [ ] Status bar color changing
    - [ ] App settings with Resource Environment (See: [Source 1](https://github.com/JetBrains/compose-multiplatform/pull/5239), [Source 2](https://github.com/JetBrains/compose-multiplatform/blob/master/components/resources/library/src/androidMain/kotlin/org/jetbrains/compose/resources/ResourceEnvironment.android.kt), [Source 3](https://youtrack.jetbrains.com/issue/CMP-4197) )
        - [ ] l10n
        - [ ] i18n
    - [ ] Testing
        - [ ] UI Testing
        - [ ] Compose Screenshot testing
