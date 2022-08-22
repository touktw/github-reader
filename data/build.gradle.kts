plugins {
    id("com.android.library") version Versions.gradle
    kotlin("android") version Versions.kotlin
    kotlin("kapt") version Versions.kotlin
}

android {
    compileSdk = Apps.compileSdk
    defaultConfig {
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
    useLibrary("android.test.base")
}

dependencies {
    val module = Modules.get(project.name)
    implementation(module.implementations)
    kapt(module.kapt)
    testImplementation(module.testImplementation)
    androidTestImplementation(module.androidTestImplementation)
}