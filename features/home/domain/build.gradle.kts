plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.home.domain"
}

dependencies {
    implementation(project(":features:home:data"))
    implementation(project(":cores:util"))
}
