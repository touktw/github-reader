plugins {
    id("com.android.application") version Versions.gradle
    kotlin("android") version Versions.kotlin
    kotlin("kapt") version Versions.kotlin
    id("com.google.dagger.hilt.android") version Versions.hilt
}

android {
    compileSdk = Apps.compileSdk
    defaultConfig {
        applicationId = "com.touktw.github"
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures.viewBinding = true
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(project(":data"))

    val module = Modules.get(project.name)
    implementation(module.implementations)
    kapt(module.kapt)
    testImplementation(module.testImplementation)
    androidTestImplementation(module.androidTestImplementation)
}
