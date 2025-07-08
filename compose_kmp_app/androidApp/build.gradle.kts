plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose") // Apply Compose plugin if you use @Composable in androidApp directly
}

android {
    namespace = "com.example.composekmpapp.android" // Replace with your app's namespace
    compileSdk = 34 // Or your target SDK

    defaultConfig {
        applicationId = "com.example.composekmpapp.android" // Replace with your app's ID
        minSdk = 24 // Or your min SDK
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7" // Ensure this matches your Kotlin version's compatibility
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.6.1") // Check for latest stable
    implementation("androidx.compose.ui:ui-tooling:1.6.1") // For preview and tools
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.1")
    implementation("androidx.compose.foundation:foundation:1.6.1")
    implementation("androidx.compose.material:material:1.6.1")
    implementation("androidx.activity:activity-compose:1.8.0") // For ComponentActivity.setContent
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
}
