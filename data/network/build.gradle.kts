plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.network)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.everest.unit.test)

    alias(libs.plugins.junit5)
}

android {
    namespace = "com.everest.network"

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"${rootProject.extra["apiKey"]}\"")
            buildConfigField("String", "BASE_URL", "\"${rootProject.extra["baseURL"]}\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"${rootProject.extra["apiKey"]}\"")
            buildConfigField("String", "BASE_URL", "\"${rootProject.extra["baseURL"]}\"")
        }
    }
}
dependencies {
    implementation(projects.cores.util)
}
