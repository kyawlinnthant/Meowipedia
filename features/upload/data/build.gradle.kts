plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.upload.data"
}

dependencies {
    api(project(":cores:util"))
    implementation(project(":cores:dispatcher"))
    api(project(":data:network"))
}
