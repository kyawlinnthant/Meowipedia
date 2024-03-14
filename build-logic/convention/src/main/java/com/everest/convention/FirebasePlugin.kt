import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class FirebasePlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val services = libs.findPlugin("google-services").get().get().pluginId
            with(pluginManager) {
                apply(services)
            }
            val bom = libs.findLibrary("firebase-bom").get()
            val firebase = libs.findBundle("firebase").get()
            dependencies {
                add("implementation", platform(bom))
                add("implementation", firebase)
            }
        }
    }
}
