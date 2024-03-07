import java.io.FileInputStream
import java.util.Properties
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
    alias(libs.plugins.detekt).apply(false)
    alias(libs.plugins.ktlint).apply(false)
    alias(libs.plugins.secrete.gradle).apply(false)
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

val credentialDp = loadCredentialData()

extra.apply {
    set("baseURL", credentialDp.getProperty("BASE_URL"))
    set("apiKey", credentialDp.getProperty("API_KEY"))
}

fun loadCredentialData(): Properties {
    val keysFile = file("credentials.properties")
    val keysProperties = Properties()
    keysProperties.load(FileInputStream(keysFile))
    return keysProperties
}
