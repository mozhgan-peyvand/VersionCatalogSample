plugins {
    id("com.android.library")
    id("mp.android.library.compose")
    id("kotlin-kapt")
    id("mp.android.library")
}

android {
    namespace = "com.example.base"

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