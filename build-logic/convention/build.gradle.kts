@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed

plugins {
    `kotlin-dsl`
}
group = "com.everest.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
}
dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)

    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("android.application") {
            id = "com.everest.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("compose.application") {
            id = "com.everest.compose.application"
            implementationClass = "ComposeApplicationPlugin"
        }
        register("android.library") {
            id = "com.everest.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("compose.library") {
            id = "com.everest.compose.library"
            implementationClass = "ComposeLibraryPlugin"
        }
        register("dagger.hilt") {
            id = "com.everest.dagger.hilt"
            implementationClass = "DaggerHiltPlugin"
        }
        register("retrofit.network") {
            id = "com.everest.retrofit.network"
            implementationClass = "NetworkPlugin"
        }
        register("room.database") {
            id = "com.everest.room.database"
            implementationClass = "DatabasePlugin"
        }
        register("preferences.datastore") {
            id = "com.everest.preferences.datastore"
            implementationClass = "DatastorePrefPlugin"
        }
        register("test.unit") {
            id = "com.everest.test.unit"
            implementationClass = "TestUnitPlugin"
        }
        register("test.android") {
            id = "com.everest.test.android"
            implementationClass = "TestAndroidPlugin"
        }
        register("firebase") {
            id = "com.everest.firebase"
            implementationClass = "FirebasePlugin"
        }
    }
}
