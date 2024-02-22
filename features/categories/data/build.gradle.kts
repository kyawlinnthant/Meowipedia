plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.categories.data"
}

dependencies {
    api(project(":data:network"))
    api(project(":cores:util"))
    api(project(":data:database"))
    implementation(project(":cores:dispatcher"))
    implementation(libs.date.time)
    implementation(libs.compose.paging)

}
