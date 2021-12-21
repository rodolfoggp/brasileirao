object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val koinViewModel = "io.insert-koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenABP}"
    const val viewBinding = "com.github.wada811:ViewBinding-ktx:${Versions.viewBinding}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitRxJava2Adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"

    const val rxJava2 = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
    const val rxJava2Android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomRxJava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    // Test Libs
    const val junit = "junit:junit:${Versions.junit}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"

    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val androidXTestRunner = "androidx.test:runner:${Versions.androidXTest}"
    const val androidXTestCore = "androidx.test:core:${Versions.androidXTest}"
    const val androidXTestExtJunitKtx = "androidx.test.ext:junit-ktx:${Versions.androidXTest}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
}