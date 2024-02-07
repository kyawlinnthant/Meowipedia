@file:Suppress("UnstableApiUsage")
pluginManagement {
    repositories {
        includeBuild("build-logic")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Meowipedia"
include(":app")
include(
    ":cores:theme",
    ":cores:navigation",
    ":cores:model",
    ":cores:ui"
)
include(
    ":data:network",
    ":data:database",
    ":data:datastore"
)
include(
    ":features:categories"
)

