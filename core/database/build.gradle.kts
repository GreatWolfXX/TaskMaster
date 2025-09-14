plugins {
    alias(libs.plugins.taskmaster.library)
    alias(libs.plugins.sqldelight)
}

android {
    namespace = "com.greatwolf.database"
}

dependencies {
    implementation(projects.core.model)

    // SQLDelight
    implementation(libs.sqldelight)

    // Koin
    implementation(libs.koin.core)
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.greatwolf")
        }
    }
}
