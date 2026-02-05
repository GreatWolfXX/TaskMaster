plugins {
    alias(libs.plugins.taskmaster.library)
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    // Koin
    implementation(libs.koin.core)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}