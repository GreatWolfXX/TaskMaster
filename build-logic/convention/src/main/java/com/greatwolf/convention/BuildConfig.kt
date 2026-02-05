package com.greatwolf.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import java.util.Properties

internal fun Project.configureBuildConfig(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        buildFeatures.apply {
            buildConfig = true
        }

        val localProperties = Properties().apply {
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                load(localPropertiesFile.inputStream())
            }
        }

        val supabaseUrl = localProperties["SUPABASE_URL"].toString()
        val supabaseKey = localProperties["SUPABASE_KEY"].toString()

        defaultConfig.apply {
            buildConfigField("String", "SUPABASE_URL", "\"$supabaseUrl\"")
            buildConfigField("String", "SUPABASE_KEY", "\"$supabaseKey\"")
        }
    }
}