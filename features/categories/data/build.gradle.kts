plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.categories.data"
}

dependencies {
    api(project(":data:network"))
    api(project(":cores:util"))
    implementation(project(":cores:dispatcher"))
}
