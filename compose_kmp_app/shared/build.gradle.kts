import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    // Define your targets
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                // Voyager for navigation (optional, but good for multiplatform)
                implementation("cafe.adriel.voyager:voyager-navigator:1.0.0")
                implementation("cafe.adriel.voyager:voyager-screenmodel:1.0.0")
                implementation("cafe.adriel.voyager:voyager-transitions:1.0.0")

                // Kotlin Test dependencies
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                // For more specific assertions, you might add libraries like AssertK or Kotest
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.8.0")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.12.0")
            }
        }
        val iosMain by getting {
            // Dependencies specific to iOS
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit")) // For running tests on Android JVM
                implementation("junit:junit:4.13.2")
            }
        }
        val iosTest by getting {
            // Dependencies for iOS tests
        }
    }
    // Optional: Configure framework generation for iOS
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.framework {
            baseName = "shared"
            isStatic = true // Or false if you prefer dynamic
        }
    }
}

android {
    namespace = "com.example.composekmpapp.shared" // Replace with your namespace
    compileSdk = 34 // Or your target SDK
    defaultConfig {
        minSdk = 24 // Or your min SDK
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        java.srcDirs("src/androidMain/kotlin") // if you have Java code
        res.srcDirs("src/androidMain/res") // if you have Android resources
    }
}
