@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.aureusapps.gradle:plugin-utils:1.0.1")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    versionCatalogs {
        create("libs") {
            from("com.aureusapps:version-catalog:1.0.6")
        }
    }
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}
rootProject.name = "android-aspect-ratio-layout"
include("aspect-ratio-layout", "example")
