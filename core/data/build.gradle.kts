import com.greatwolf.convention.implementation

plugins {
    alias(libs.plugins.taskmaster.library)
}

android {
    namespace = "com.greatwolf.data"
}

dependencies {
    implementation(projects.core.datastore)
    implementation(projects.core.domain)
//    implementation(projects.core.models)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Koin
    implementation(libs.koin.core)
}