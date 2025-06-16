import com.android.build.api.dsl.LibraryExtension
import com.greatwolf.convention.configureKotlinAndroid
import com.greatwolf.convention.implementation
import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("android-library").get().get().pluginId)
                apply(libs.findPlugin("kotlin-android").get().get().pluginId)
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            dependencies {
                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
            }
        }
    }
}