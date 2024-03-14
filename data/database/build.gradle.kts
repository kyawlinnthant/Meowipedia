plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.database)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.junit5)
    alias(libs.plugins.everest.unit.test)
    alias(libs.plugins.everest.android.test)
}

android {
    namespace = "com.everest.database"
    defaultConfig {
        testInstrumentationRunner = "com.everest.database.DatabaseTestRunner"
    }
}

dependencies {
    implementation(projects.cores.testRule)
}
