import com.android.build.api.dsl.ApplicationExtension
import com.everest.convention.AppConfig
import com.everest.convention.plugins.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val app = libs.findPlugin("android-application").get().get().pluginId
            val kotlin = libs.findPlugin("kotlin-android").get().get().pluginId
            val serialization = libs.findPlugin("kotlin-serialization").get().get().pluginId
            val secret = libs.findPlugin("secret-gradle").get().get().pluginId
            with(pluginManager) {
                apply(app)
                apply(kotlin)
                apply(serialization)
                apply(secret)
            }
            extensions.configure<ApplicationExtension> {
                configureKotlin(this)
                defaultConfig.targetSdk = AppConfig.TARGET_SDK
            }
            val cores = libs.findBundle("androidx-cores").get()
            dependencies {
                add("implementation", cores)
            }
        }
    }
}
