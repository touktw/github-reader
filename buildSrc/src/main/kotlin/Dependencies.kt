import org.gradle.api.artifacts.dsl.DependencyHandler

object Apps {
    const val compileSdk = 31
    const val minSdk = 21
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "7.2.2"
    const val kotlin = "1.6.10"
    const val appcompat = "1.5.0"
    const val constraintLayout = "2.1.4"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.3"
    const val androidxCore = "1.8.0"

    const val coroutines = "1.6.1"
}

object Libs {

    // kotlin std lib
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // android ui
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3.okhttp:${Versions.okhttp}"

    // coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"




    val appLibraries = listOf(
        kotlin,
        appcompat,
        androidxCore,
        constraintLayout,
        retrofit,
        retrofitMoshiConverter,
        okhttp,
        coroutinesCore,
        coroutinesAndroid
    )
}

fun DependencyHandler.implementation(list:List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.kapt(list:List<String>) {
    list.forEach { dependency->
        add("kapt", dependency)
    }
}