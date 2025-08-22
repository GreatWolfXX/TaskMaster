import com.greatwolf.convention.implementation
import com.greatwolf.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class SupabaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                val bom = libs.findLibrary("supabase-bom").get()
                implementation(platform(bom))
                implementation(libs.findLibrary("supabase-auth").get())
                implementation(libs.findLibrary("supabase-postgrest").get())
                implementation(libs.findLibrary("supabase-storage").get())
                implementation(libs.findLibrary("ktor-client-cio").get())

                // Test
                implementation(libs.findLibrary("ktor-client-mock").get())
            }
        }
    }
}