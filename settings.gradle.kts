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
        maven { setUrl("https://www.jitpack.io") }
    }
}

rootProject.name = "WeatherApp"
include(":app")
include(":network")
include(":core:ui-commons")
include(":feature:city-weather")
include(":core:domain")
include(":data")
include(":core:data")
include(":core:domain-data")
include(":util")
