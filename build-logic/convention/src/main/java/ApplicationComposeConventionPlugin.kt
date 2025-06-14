import com.android.build.api.dsl.ApplicationExtension
import com.greatwolf.convention.configureAndroidCompose
import com.greatwolf.convention.configureKotlinAndroid
import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(libs.findPlugin("android-application").get().get().pluginId)
                apply(libs.findPlugin("kotlin-android").get().get().pluginId)
                apply(libs.findPlugin("kotlin-compose").get().get().pluginId)
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                defaultConfig.targetSdk = libs.findVersion("targetSdk").get().toString().toInt()

            }
        }
    }
}