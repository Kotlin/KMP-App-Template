# Kotlin Multiplatform app template

[![official project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is a basic Kotlin Multiplatform app template for Android, iOS, desktop, and web. It includes shared business logic and data handling, and a shared UI implementation using Compose Multiplatform.

> The template is also available [with native UI written in Jetpack Compose and SwiftUI](https://github.com/kotlin/KMP-App-Template-Native).
>
> The [`kotlin-toolchain` branch](https://github.com/Kotlin/KMP-App-Template/tree/kotlin-toolchain) showcases the same project configured with the [Kotlin Toolchain](https://github.com/JetBrains/kotlin-toolchain).

![Screenshots of the app](images/screenshots.png)

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar (make sure that you have the [Kotlin Multiplatform plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform) installed.

You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- Desktop app: `./gradlew :desktopApp:hotRun --auto`
- Web app: `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
- iOS app: open the iOS project in Xcode and run it from there.

### Technologies

The data displayed by the app is from [The Metropolitan Museum of Art Collection API](https://metmuseum.github.io/).

The app uses the following multiplatform dependencies in its implementation:

- [Compose Multiplatform](https://jb.gg/compose) for UI
- [Compose Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)
- [Ktor](https://ktor.io/) for networking
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) for JSON handling
- [Coil](https://github.com/coil-kt/coil) for image loading
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection

> These are just some of the possible libraries to use for these tasks with Kotlin Multiplatform, and their usage here isn't a strong recommendation for these specific libraries over the available alternatives. You can find a wide variety of curated multiplatform libraries in the [kmp-awesome](https://github.com/terrakok/kmp-awesome) repository.
