plugins {
    alias(libs.plugins.everest.android.application)
    alias(libs.plugins.everest.compose.application)
    alias(libs.plugins.everest.hilt)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.everest.meowipedia"

    defaultConfig {
        applicationId = "com.everest.meowipedia"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z ( Major.Minor.Patch)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
dependencies {
    implementation(project(":features:home:presentation"))
    implementation(project(":features:settings:presentation"))
    implementation(project(":features:upload:presentation"))
    implementation(project(":features:settings:domain"))
    implementation(project(":cores:navigation"))
    implementation(project(":cores:file-utils"))
    implementation(project(":cores:model"))
    implementation(project(":cores:theme"))

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
