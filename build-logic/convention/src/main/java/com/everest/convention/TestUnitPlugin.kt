import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class TestUnitPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val unitTest = libs.findBundle("unit-test").get()
            dependencies {
                add("testImplementation", unitTest)
            }
        }
    }
}