import com.greatwolf.convention.implementation
import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("taskmaster.library").get().get().pluginId)
                apply(libs.findPlugin("taskmaster.library.compose").get().get().pluginId)
                apply(libs.findPlugin("taskmaster.navigation").get().get().pluginId)
            }

            dependencies {
                implementation(project(":core:ui"))
//                implementation(project(":core:model"))
                implementation(project(":core:domain"))
//                implementation(project(":core:common"))

                implementation(libs.findLibrary("koin-androidx-compose").get())
            }
        }
    }
}