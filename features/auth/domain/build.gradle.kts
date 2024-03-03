plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.auth.domain"
}

dependencies {
    implementation(project(":features:auth:data"))
    implementation(project(":cores:util"))
}
