plugins {
    alias(libs.plugins.taskmaster.feature)
}

android {
    namespace = "com.greatwolf.auth"
}

dependencies {
    implementation(projects.core.model)
}