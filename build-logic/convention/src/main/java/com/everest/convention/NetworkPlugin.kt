import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class NetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val secret = libs.findPlugin("secret-gradle").get().get().pluginId
            with(pluginManager) {
                apply(secret)
            }
            val network = libs.findBundle("network").get()
            dependencies {
                add("api", network)
            }
        }
    }
}
