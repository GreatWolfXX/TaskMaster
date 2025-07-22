plugins {
    alias(libs.plugins.taskmaster.library)
}

android {
    namespace = "com.greatwolf.datastore"
}

dependencies {
    // Koin
    implementation(libs.koin.core)

    // DataStore
    implementation(libs.datastore.preferences)
}