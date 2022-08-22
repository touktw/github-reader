object Versions {
    const val gradle = "7.2.2"
    const val kotlin = "1.6.10"
    const val appcompat = "1.5.0"
    const val constraintLayout = "2.1.4"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.3"
    const val androidxCore = "1.8.0"
    const val coroutines = "1.6.1"
    const val dataStore = "1.0.0"
    const val moshi = "1.12.0"
    const val room = "2.4.3"
    const val lifecycleViewModelKtx = "2.5.1"
    const val hilt = "2.43.2"
    const val activity = "1.5.1"
    const val fragment = "1.5.1"

    // test
    const val junit = "4.13"
    const val mockito = "4.6.1"
    const val robolectric = "4.7.3"

    // android test
    const val androidTestCore = "1.4.0"
    const val androidJunit = "1.1.3"
    const val sandwich = "1.2.7"
}

object Libraries {
    // kotlin std lib
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    // android
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"


    // hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val sandwich = "com.github.skydoves:sandwich:${Versions.sandwich}"

    // coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // data store
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    // room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompilerKapt = "androidx.room:room-compiler:${Versions.room}"


    // test
    const val junit = "junit:junit:${Versions.junit}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKtx = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    const val testCoroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"


    // android test
    const val androidTestCore = "androidx.test:core:${Versions.androidTestCore}"
    const val androidTestCoreKtx = "androidx.test:core-ktx:${Versions.androidTestCore}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val androidJunitKtx = "androidx.test.ext:junit-ktx:${Versions.androidJunit}"

    val commonLibraries = listOf(
        kotlin,
        androidxCore,
        coroutinesCore,
        coroutinesAndroid,
        hiltAndroid
    )

    val commonKapt = listOf(
        hiltCompiler
    )


    val testLibraries = listOf(
        junit,
        androidTestCore,
        robolectric,
        testCoroutine
    )

    val androidTestLibraries = listOf(
        androidJunit,
        androidJunitKtx,
        androidTestCore,
        androidTestCoreKtx
    )

}

object Modules {
    fun get(name: String): Module {
        return modules[name] ?: throw NullPointerException("Can not find module: $name")
    }

    private val modules = mapOf(
        "app" to Module(
            implementations = Libraries.commonLibraries + listOf(
                Libraries.appcompat,
                Libraries.constraintLayout,
                Libraries.lifecycleViewModelKtx,
                Libraries.fragment,
                Libraries.activity
            ),
            kapt = Libraries.commonKapt,
            testImplementation = Libraries.testLibraries,
            androidTestImplementation = Libraries.androidTestLibraries
        ),
        "data" to Module(
            implementations = Libraries.commonLibraries + listOf(
                Libraries.okhttp,
                Libraries.okhttpLogging,
                Libraries.retrofit,
                Libraries.retrofitMoshiConverter,
                Libraries.dataStore,
                Libraries.moshi,
                Libraries.moshiKotlin,
                Libraries.roomRuntime,
                Libraries.roomKtx,
                Libraries.sandwich,
            ),
            kapt = Libraries.commonKapt + listOf(Libraries.roomCompilerKapt),
            testImplementation = Libraries.testLibraries,
            androidTestImplementation = Libraries.androidTestLibraries
        )
    )

    data class Module(
        val implementations: List<String> = emptyList(),
        val kapt: List<String> = emptyList(),
        val testImplementation: List<String> = emptyList(),
        val androidTestImplementation: List<String> = emptyList(),
    )
}