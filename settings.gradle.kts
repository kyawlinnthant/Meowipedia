@file:Suppress("UnstableApiUsage")

include(":firebase")



enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
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

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "Meowipedia"
include(":app")

include(":cores:file-utils")

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
    ":features:collection:data",
    ":features:collection:domain",
    ":features:collection:presentation",
)

