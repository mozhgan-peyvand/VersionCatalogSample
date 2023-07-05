
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("mp.android.library.compose")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.library"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.retrofit)
    implementation(libs.retrofitMoshiConverter)

    implementation(libs.moshi)
    kapt(libs.moshiCodegen)
}