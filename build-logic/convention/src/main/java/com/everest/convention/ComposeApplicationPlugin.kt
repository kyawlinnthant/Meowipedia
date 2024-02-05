import com.android.build.api.dsl.ApplicationExtension
import com.everest.convention.plugins.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val androidApplication = "com.android.application"

        with(target) {
            with(pluginManager) {
                apply(androidApplication)
            }
            extensions.configure<ApplicationExtension> {
                configureCompose(this)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val ui = libs.findBundle("androidx-compose").get()
            val debug = libs.findBundle("androidx-compose-debug").get()
            dependencies {
                add("implementation", ui)
                add("debugImplementation", debug)
            }
        }
    }
}
