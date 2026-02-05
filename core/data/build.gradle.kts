plugins {
    alias(libs.plugins.taskmaster.library.android)
    alias(libs.plugins.taskmaster.supabase)
}

android {
    namespace = "com.greatwolf.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.domain)
    implementation(projects.core.model)

    // DataStore
    implementation(libs.datastore.preferences)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Koin
    implementation(libs.koin.core)
}