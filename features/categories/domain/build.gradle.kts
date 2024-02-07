plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.categories.domain"
}

dependencies{
    implementation(project(":features:categories:data"))
}