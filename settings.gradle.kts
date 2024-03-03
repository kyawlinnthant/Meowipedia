@file:Suppress("UnstableApiUsage")

include(":cores:file-utils")

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
    ":cores:ui",
    ":cores:util",
    ":cores:dispatcher",
    ":cores:test-rule"
)
include(
    ":data:network",
    ":data:database",
    ":data:datastore",
)
include(
    ":features:auth:data",
    ":features:auth:domain",
    ":features:auth:presentation",
)
include(
    ":features:home:data",
    ":features:home:domain",
    ":features:home:presentation",
)
include(
    ":features:settings:data",
    ":features:settings:domain",
    ":features:settings:presentation",
)
include(
    ":features:upload:data",
    ":features:upload:domain",
    ":features:upload:presentation",
)

