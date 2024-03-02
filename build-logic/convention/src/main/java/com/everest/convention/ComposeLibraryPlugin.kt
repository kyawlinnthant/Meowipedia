import com.android.build.gradle.LibraryExtension
import com.everest.convention.plugins.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val library = libs.findPlugin("android-library").get().get().pluginId
            with(pluginManager) {
                apply(library)
            }
            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }
            val ui = libs.findBundle("androidx-compose").get()
            val debug = libs.findBundle("androidx-compose-debug").get()
            dependencies {
                add("implementation", ui)
                add("debugImplementation", debug)
            }
        }
    }
}
