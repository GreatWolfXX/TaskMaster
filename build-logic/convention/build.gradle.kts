import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.greatwolf.taskmaster.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("applicationCompose") {
            id = libs.plugins.taskmaster.application.compose.get().pluginId
            implementationClass = "ApplicationComposeConventionPlugin"
        }

        register("library") {
            id = libs.plugins.taskmaster.library.asProvider().get().pluginId
            implementationClass = "LibraryConventionPlugin"
        }

        register("libraryCompose") {
            id = libs.plugins.taskmaster.library.compose.get().pluginId
            implementationClass = "LibraryComposeConventionPlugin"
        }

        register("feature") {
            id = libs.plugins.taskmaster.feature.get().pluginId
            implementationClass = "FeatureConventionPlugin"
        }
    }
}
