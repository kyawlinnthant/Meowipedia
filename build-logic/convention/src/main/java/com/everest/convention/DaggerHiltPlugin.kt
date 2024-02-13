import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DaggerHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val googleKsp = "com.google.devtools.ksp"
        val googleHilt = "com.google.dagger.hilt.android"

        with(target) {
            with(pluginManager) {
                apply(googleKsp)
                apply(googleHilt)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val hiltAndroid = libs.findLibrary("google-hilt-android").get()
            val hiltCompiler = libs.findLibrary("google-hilt-compiler").get()
            val hiltTest = libs.findLibrary("google-hilt-test").get()
            dependencies {
                add("implementation", hiltAndroid)
                add("ksp", hiltCompiler)
                add("androidTestApi", hiltTest)
            }
        }
    }
}
