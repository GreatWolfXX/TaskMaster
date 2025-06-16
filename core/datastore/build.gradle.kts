import com.greatwolf.convention.implementation

plugins {
    alias(libs.plugins.taskmaster.library)
}

android {
    namespace = "com.greatwolf.datastore"
}

dependencies {
    // DataStore
    implementation(libs.datastore.preferences)
}