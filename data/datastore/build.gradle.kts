plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.datastore)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.datastore"
    defaultConfig {
        testInstrumentationRunner = "com.everest.datastore.DataStoreTestRunner"
    }
}
dependencies {
    implementation(project(":cores:dispatcher"))
    implementation(project(":cores:test-rule"))
}
