import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

private val detekt = libs.plugins.detekt.get().pluginId
private val ktlint = libs.plugins.ktlint.get().pluginId

plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.google.hilt).apply(false)
    alias(libs.plugins.google.ksp).apply(false)
    alias(libs.plugins.secret.gradle).apply(false)
    alias(libs.plugins.google.services).apply(false)
    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.ktlint).apply(false)
}

subprojects {
    apply(plugin = detekt)
    apply(plugin = ktlint)
    configure<KtlintExtension> {
        debug.set(true)
        verbose.set(true)
        android.set(true)
    }
    configure<DetektExtension> {
        parallel = true
        allRules = true
        autoCorrect = true
        buildUponDefaultConfig = true
        config.setFrom(file("${rootProject.rootDir}/config/detekt/detekt.yml"))
    }
}

