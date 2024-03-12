plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.network)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
    alias(libs.plugins.junit5)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.everest.network"

    buildFeatures {
        buildConfig = true
    }
}
dependencies {
    implementation(projects.cores.util)
}

secrets {
    // Change the properties file from the default "local.properties" in your root project
    // to another properties file in your root project.
    propertiesFileName = "credentials.properties"

    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "secrets.defaults.properties"
    ignoreList.add("keyToIgnore")
}

