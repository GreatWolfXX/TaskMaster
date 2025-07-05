import com.greatwolf.convention.implementation

plugins {
    alias(libs.plugins.taskmaster.library)
}

android {
    namespace = "com.greatwolf.domain"
}

dependencies {
    implementation(projects.core.common)
//    implementation(projects.core.models)

    // Koin
    implementation(libs.koin.core)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}