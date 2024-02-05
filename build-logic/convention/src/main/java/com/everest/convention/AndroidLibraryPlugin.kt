import com.android.build.gradle.LibraryExtension
import com.everest.convention.AppConfig
import com.everest.convention.plugins.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val androidLibrary = "com.android.library"
        val androidKotlin = "org.jetbrains.kotlin.android"
        val serialization = "org.jetbrains.kotlin.plugin.serialization"
        with(target) {
            with(pluginManager) {
                apply(androidLibrary)
                apply(androidKotlin)
                apply(serialization)
            }
            extensions.configure<LibraryExtension> {
                configureKotlin(this)
                defaultConfig.targetSdk = AppConfig.TARGET_SDK
            }
            dependencies {
                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))
            }
        }
    }
}

