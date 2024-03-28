plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.network)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
    alias(libs.plugins.junit5)
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
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
//    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*") // Ignore all keys matching the regexp "sdk.*"
}
