pluginManagement {
    repositories {
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
        maven("https://jitpack.io")
    }
    versionCatalogs {
        create("libs") {
            from(files("gradle/TorangToml/libs.versions.toml"))
        }
    }
}

rootProject.name = "RestaurantOverView"
include(":app")
include(":library")
