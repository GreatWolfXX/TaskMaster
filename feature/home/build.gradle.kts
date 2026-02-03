plugins {
    alias(libs.plugins.taskmaster.feature)
}

android {
    namespace = "com.greatwolf.home"
}

dependencies {
    implementation(projects.core.model)
}