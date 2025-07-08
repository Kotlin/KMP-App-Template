buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0") // Or a recent stable version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21") // Or a recent stable version
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.6.0-beta02") // Or a recent stable version
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
