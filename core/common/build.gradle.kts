import com.greatwolf.convention.implementation

plugins {
    alias(libs.plugins.taskmaster.library)
}

android {
    namespace = "com.greatwolf.common"
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
}