plugins {
    alias(libs.plugins.taskmaster.library.compose)
}

android {
    namespace = "com.greatwolf.ui"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    // Google Fonts
    implementation(libs.androidx.ui.text.google.fonts)

    // OhTeePee
    implementation(libs.ohteepee)
}