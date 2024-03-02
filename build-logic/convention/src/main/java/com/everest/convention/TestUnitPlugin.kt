import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class TestUnitPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val mockWebServer = libs.findLibrary("mockwebserver").get()
            val coroutines = libs.findLibrary("coroutines-test").get()
            val jupiterApi = libs.findLibrary("jupiter-api").get()
            val jupiterEngine = libs.findLibrary("jupiter-engine").get()
            val jupiterParams = libs.findLibrary("jupiter-param").get()
            val assertk = libs.findLibrary("assertk").get()
            val mockk = libs.findLibrary("mockk").get()

            dependencies {
                add("testImplementation", mockWebServer)
                add("testRuntimeOnly", jupiterEngine)
                add("testImplementation", coroutines)
                add("testImplementation", jupiterApi)
                add("testImplementation", jupiterParams)
                add("testImplementation", assertk)
                add("testImplementation", mockk)
            }
        }
    }
}
