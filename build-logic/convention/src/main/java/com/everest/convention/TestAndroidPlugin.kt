import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class TestAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val coroutinesTest = libs.findLibrary("coroutines-test").get()
            val coroutinesCore = libs.findLibrary("coroutines-core").get()
            val jupiterApi = libs.findLibrary("jupiter-api").get()
            val jupiterEngine = libs.findLibrary("jupiter-engine").get()
            val jupiterParams = libs.findLibrary("jupiter-param").get()
            val assertk = libs.findLibrary("assertk").get()
            val mockk = libs.findLibrary("mockk").get()
            val turbine = libs.findLibrary("turbine").get()

            dependencies {
                add("androidTestRuntimeOnly", jupiterEngine)
                add("androidTestImplementation", coroutinesTest)
                add("androidTestImplementation", coroutinesCore)
                add("androidTestImplementation", jupiterApi)
                add("androidTestImplementation", jupiterParams)
                add("androidTestImplementation", assertk)
                add("androidTestImplementation", mockk)
                add("androidTestImplementation", turbine)
            }
        }
    }
}
