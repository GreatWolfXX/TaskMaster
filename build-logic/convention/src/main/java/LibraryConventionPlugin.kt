import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("jetbrains-kotlin-jvm").get().get().pluginId)
            }
        }
    }
}