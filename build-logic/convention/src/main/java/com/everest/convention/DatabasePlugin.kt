import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DatabasePlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val ksp = libs.findPlugin("google-ksp").get().get().pluginId
            with(pluginManager) {
                apply(ksp)
            }
            val room = libs.findBundle("room").get()
            val roomCompiler = libs.findLibrary("room-compiler").get()
            val runner = libs.findLibrary("androidx-runner").get()
            dependencies {
                add("api",runner)
                add("api", room)
                add("ksp", roomCompiler)
            }
        }
    }
}
