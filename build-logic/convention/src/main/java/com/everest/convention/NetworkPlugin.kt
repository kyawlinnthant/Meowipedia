import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class NetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val serialization = "org.jetbrains.kotlin.plugin.serialization"

        with(target) {
            with(pluginManager) {
                apply(serialization)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val network = libs.findBundle("network").get()
            dependencies {
                add("implementation", network)
            }
        }
    }
}