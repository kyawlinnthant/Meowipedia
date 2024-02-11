plugins {
    alias(libs.plugins.everest.android.library)
    alias(libs.plugins.everest.network)
    alias(libs.plugins.everest.hilt)
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
    implementation(project(":cores:util"))
    testImplementation("com.google.truth:truth:1.4.0")
}


