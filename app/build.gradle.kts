plugins {
    alias(libs.plugins.taskmaster.application.compose)
    alias(libs.plugins.taskmaster.navigation)
}

android {
    namespace = "com.greatwolf.taskmaster"

    defaultConfig {
        applicationId = "com.greatwolf.taskmaster"
        versionCode = 1
        versionName = "0.11.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.datastore)
    implementation(projects.core.domain)
    implementation(projects.core.models)
    implementation(projects.core.ui)

    implementation(projects.feature.onboarding)
    implementation(projects.feature.auth)

    // Koin
    implementation(libs.koin.androidx.compose)
}