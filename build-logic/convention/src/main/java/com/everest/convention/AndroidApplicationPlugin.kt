import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import com.android.build.api.dsl.ApplicationExtension
import com.everest.convention.AppConfig
import com.everest.convention.plugins.configureKotlin

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val androidApplication = "com.android.application"
        val androidKotlin = "org.jetbrains.kotlin.android"

        with(target) {
            with(pluginManager) {
                apply(androidApplication)
                apply(androidKotlin)
            }
            extensions.configure<ApplicationExtension> {
                configureKotlin(this)
                defaultConfig.targetSdk = AppConfig.TARGET_SDK
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val cores = libs.findBundle("androidx-cores").get()
            dependencies {
                add("implementation",cores)
            }
        }
    }
}
