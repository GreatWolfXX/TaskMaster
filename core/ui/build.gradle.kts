import com.greatwolf.convention.implementation

plugins {
    alias(libs.plugins.taskmaster.library.compose)
}

android {
    namespace = "com.greatwolf.ui"
}

dependencies {
    implementation(projects.core.common)

    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)
}