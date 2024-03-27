plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.datastore)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = libs.versions.data.datastore.get()
    defaultConfig {
        testInstrumentationRunner = "com.everest.datastore.DataStoreTestRunner"
    }
}
dependencies {
    implementation(projects.cores.dispatcher)
    implementation(projects.cores.testRule)
    implementation(projects.cores.model)
}
