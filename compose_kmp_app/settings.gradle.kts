pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "ComposeKMPApp"

include(":androidApp")
include(":shared")
// For iosApp, the actual inclusion might be handled differently depending on
// how the Xcode project is integrated, often it's not directly included here
// if it's managed by Xcode building the shared framework.
// However, if you build a KMM framework for iOS via Gradle, you might include it.
// For now, we'll focus on shared and androidApp in this Gradle setup.
