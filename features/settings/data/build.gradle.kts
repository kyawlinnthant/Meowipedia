plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = libs.versions.features.settings.data.get()
}

dependencies {
    api(projects.data.datastore)
    api(projects.cores.model)
}
