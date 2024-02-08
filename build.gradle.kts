import java.io.FileInputStream
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.google.hilt).apply(false)
    alias(libs.plugins.google.ksp).apply(false)
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