plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.home.data"
}
dependencies {
    api(project(":data:network"))
    api(project(":data:database"))
    api(project(":cores:util"))
    implementation(project(":cores:dispatcher"))
    api(libs.date.time)
}
