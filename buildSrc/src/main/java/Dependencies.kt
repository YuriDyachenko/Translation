import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "yuri.dyachenko.translation"
    const val compileSdk = 31
    const val minSdk = 24
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"
    val java_version = JavaVersion.VERSION_1_8
}

object Versions {
    const val core = "1.7.0"
    const val appcompat = "1.4.0"
    const val material = "1.4.0"
    const val constraintLayout = "2.1.2"
    const val recyclerview = "1.2.1"
    const val retrofit = "2.9.0"
    const val retrofitGson = "2.9.0"
    const val retrofitCoroutines = "0.9.2"
    const val koin = "3.1.3"
    const val picasso = "2.71828"
    const val room = "2.3.0"
    const val kirich1409 = "1.5.0-beta01"
    const val cicerone = "7.1"
    const val coroutines = "1.5.2"
    const val interceptor = "4.9.2"
    const val jUnit = "4.13.2"
    const val ext = "1.1.3"
    const val espressoCore = "3.4.0"
}

object ViewBinding {
    const val kirich1409 = "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.kirich1409}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}

object Cicerone {
    const val core = "com.github.terrakok:cicerone:${Versions.cicerone}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Retrofit {
    const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGson}"
    const val coroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
}

object LoggingInterceptor {
    const val core = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
}

object Picasso {
    const val core = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    const val kapt = "androidx.room:room-compiler:${Versions.room}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext = "androidx.test.ext:junit:${Versions.ext}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}