plugins {
    alias(libs.plugins.taskmaster.library)
    alias(libs.plugins.sqldelight)
}

android {
    namespace = "com.greatwolf.database"
}

dependencies {

    // SQLDelight
    implementation(libs.sqldelight)
}

sqldelight {
    databases {
        create("TaskMaster") {
            packageName.set("com.greatwolf")
        }
    }
}
