package com.everest.convention.plugins

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureCompose(
    extension: CommonExtension<*, *, *, *, *>
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val composeBom = libs.findLibrary("compose-bom").get()
    val composeCompiler = libs.findVersion("compose-compiler").get().toString()
    extension.apply {
        buildFeatures {
            compose = true
        }
        defaultConfig {
            vectorDrawables {
                useSupportLibrary = true
            }
        }
        composeOptions {
            kotlinCompilerExtensionVersion = composeCompiler
        }
        dependencies {
            add("implementation", platform(composeBom))
            add("androidTestImplementation", platform(composeBom))
        }
    }
}
