plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.gradleAndroid)
    compileOnly(libs.koltinGradlePlugin)
}

gradlePlugin {
//    plugins {
//        register("androidHilt") {
//            id = "cup.android.hilt"
//            implementationClass = "AndroidHiltConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidRoom") {
//            id = "cup.android.room"
//            implementationClass = "AndroidRoomConventionPlugin"
//        }
//    }
    plugins {
        register("androidRetrofit") {
            id = "mp.android.retrofit"
            implementationClass = "AndroidRetrofitConventionPlugin"
        }
    }
    plugins {
        register("androidLibraryCompose") {
            id = "mp.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }

    plugins {
        register("androidBuildTypes") {
            id = "cup.android.buildTypes"
            implementationClass = "AndroidBuildTypesConventionPlugin"
        }
    }
//    plugins {
//        register("androidLibrary") {
//            id = "cup.android.library"
//            implementationClass = "AndroidLibraryConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidApplicationCompose") {
//            id = "cup.android.application.compose"
//            implementationClass = "AndroidApplicationComposeConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidApplication") {
//            id = "cup.android.application"
//            implementationClass = "AndroidApplicationConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidFeature") {
//            id = "cup.android.feature"
//            implementationClass = "AndroidFeatureConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidKapt") {
//            id = "cup.android.kapt"
//            implementationClass = "AndroidKaptConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidStoreFlavors") {
//            id = "cup.android.store.flavors"
//            implementationClass = "AndroidStoreFlavorsConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidSourceFlavors") {
//            id = "cup.android.source.flavors"
//            implementationClass = "AndroidSourceFlavorsConventionPlugin"
//        }
//    }
//    plugins {
//        register("androidBuildTypes") {
//            id = "cup.android.buildTypes"
//            implementationClass = "AndroidBuildTypesConventionPlugin"
//        }
//    }
}
