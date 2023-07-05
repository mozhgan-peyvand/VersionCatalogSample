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

    plugins {
        register("androidSourceFlavors") {
            id = "cup.android.source.flavors"
            implementationClass = "AndroidSourceFlavorsConventionPlugin"
        }
    }
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

    plugins {
        register("androidLibrary") {
            id = "mp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }

    plugins{

        register("androidApp"){
            id = "mp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }

}
