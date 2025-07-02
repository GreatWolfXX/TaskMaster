package com.greatwolf.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().toString().toInt()
        }

        compileOptions {
            val projectJavaVersion = JavaVersion.toVersion(libs.findVersion("javaVersion").get())
            sourceCompatibility = projectJavaVersion
            targetCompatibility = projectJavaVersion
        }

        dependencies {
            //Test
            testImplementation(libs.findLibrary("junit").get())
            testImplementation(libs.findLibrary("mockk").get())
            testImplementation(libs.findLibrary("kotlinx-coroutines-test").get())
        }
    }

    configureKotlin()
}

private fun Project.configureKotlin() {
    extensions.configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}