plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.database)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.database"
    defaultConfig {
        testInstrumentationRunner = "com.everest.database.DatabaseTestRunner"
    }
}
dependencies {
    implementation(libs.date.time)
    implementation(libs.androidx.runner)
}
