@file:Suppress("UnstableApiUsage")



include(":features:auth")


include(":features:upload")


include(":features:favourite")



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
    ":features:categories:data",
    ":features:categories:domain",
    ":features:categories:presentation",
)
include(
    ":features:settings:data",
    ":features:settings:domain",
    ":features:settings:presentation",
)

