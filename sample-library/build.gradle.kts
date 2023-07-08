
plugins {
    id("com.android.library")
    id("mp.android.library.compose")
    id("kotlin-kapt")
    id("mp.android.library")
}

android {
    namespace = "com.example.library"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.retrofitMoshiConverter)
    implementation(libs.loggingInterceptor)

    implementation(libs.moshi)
    kapt(libs.moshiCodegen)
}