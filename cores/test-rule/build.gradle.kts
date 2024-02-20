plugins {
    id("kotlin")
}

group = "com.everest.testrule"
dependencies {
    api(libs.coroutines.test)
    api(libs.junit)
}
