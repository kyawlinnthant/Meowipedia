plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)
}

android {
    namespace = "com.everest.home.data"

    tasks.withType<Test> {
        useJUnitPlatform() // Make all tests use JUnit 5
    }
}
dependencies {
    api(projects.data.network)
    api(projects.data.database)
    api(projects.cores.util)
    api(projects.cores.dispatcher)
}
