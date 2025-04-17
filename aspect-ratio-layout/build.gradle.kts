@file:Suppress("UnstableApiUsage")

import com.aureusapps.gradle.PublishLibraryConstants.GROUP_ID
import com.aureusapps.gradle.PublishLibraryConstants.VERSION_NAME

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.aureusapps.gradle.update-version")
    id("com.aureusapps.gradle.publish-library")
}

class Props(project: Project) {
    val groupId = project.findProperty(GROUP_ID) as String
    val versionName = project.findProperty(VERSION_NAME) as String
}

val props = Props(project)

android {
    namespace = "${props.groupId}.aspectratiolayout"
    compileSdk = 35
    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

publishLibrary {
    groupId.set(props.groupId)
    artifactId.set("aspect-ratio-layout")
    versionName.set(props.versionName)
    libName.set("AspectRatioLayout")
    libDescription.set("An android layout that sizes its children to a specific aspect ratio.")
    libUrl.set("https://github.com/UdaraWanasinghe/aspect-ratio-layout")
    licenseName.set("MIT License")
    licenseUrl.set("https://github.com/UdaraWanasinghe/aspect-ratio-layout/blob/main/LICENSE")
    devId.set("UdaraWanasinghe")
    devName.set("Udara Wanasinghe")
    devEmail.set("udara.developer@gmail.com")
    scmConnection.set("scm:git:https://github.com/UdaraWanasinghe/aspect-ratio-layout.git")
    scmDevConnection.set("scm:git:ssh://git@github.com/UdaraWanasinghe/aspect-ratio-layout.git")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.aureusapps.extensions)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}
