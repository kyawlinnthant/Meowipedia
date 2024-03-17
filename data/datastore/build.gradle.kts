plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.datastore)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.junit5)
    alias(libs.plugins.everest.unit.test)
    alias(libs.plugins.everest.android.test)
}

android {
    namespace = "com.everest.datastore"
    defaultConfig {
        testInstrumentationRunner = "com.everest.datastore.DataStoreTestRunner"
    }
}
dependencies {
    implementation(projects.cores.dispatcher)
    implementation(projects.cores.testRule)
    implementation(projects.cores.model)
    implementation(libs.androidx.runner)
}
