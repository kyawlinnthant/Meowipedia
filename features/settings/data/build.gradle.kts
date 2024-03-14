plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.settings.data"
}

dependencies {
    api(projects.data.datastore)
    api(projects.cores.model)
}
