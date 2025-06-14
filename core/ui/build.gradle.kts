plugins {
    alias(libs.plugins.taskmaster.library)
    alias(libs.plugins.taskmaster.library.compose)
}

android {
    namespace = "com.greatwolf.ui"
}

dependencies {
    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)
}