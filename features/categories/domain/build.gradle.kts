plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
}

android {
    namespace = "com.everest.categories.domain"
}

dependencies {
    implementation(project(":features:categories:data"))
    testImplementation("io.mockk:mockk:1.13.9")
    androidTestImplementation("io.mockk:mockk:1.13.9")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("com.google.truth:truth:1.4.0")
}