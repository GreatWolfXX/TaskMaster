import com.android.build.api.dsl.LibraryExtension
import com.greatwolf.convention.configureKotlinAndroid
import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibraryAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("android-library").get().get().pluginId)
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
        }
    }
}