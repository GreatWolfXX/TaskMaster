import com.greatwolf.convention.implementation
import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class NavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("jetbrains-kotlin-serialization").get().get().pluginId)
            }

            dependencies {
                implementation(libs.findLibrary("androidx-navigation3-runtime").get())
                implementation(libs.findLibrary("androidx-navigation3-ui").get())
                implementation(libs.findLibrary("androidx-lifecycle-viewmodel-navigation3").get())
                implementation(libs.findLibrary("kotlinx-serialization-core").get())
            }
        }
    }
}