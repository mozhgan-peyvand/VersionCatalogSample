package util

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import util.MpVersions.versionCode
import util.MpVersions.versionName

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = MpVersions.compileSdk

        defaultConfig {
            minSdk = MpVersions.minSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//            if you want to access versionName and versionCode as fields in the BuildConfig class,
//            you can use buildConfigField as shown in your code snippet . Otherwise,
//            you can simply set versionName and versionCode directly within defaultConfig without
//            using buildConfigField .
//
//            versionName = versionName
//            versionCode = versionCode

            buildConfigField(
                type = "long",
                name = "VERSION_CODE",
                value = "$versionCode"
            )
            buildConfigField(
                type = "String",
                name = "VERSION_NAME",
                value = "\"$versionName\""
            )
            buildConfigField(
                type = "String",
                name = "APPLICATION_ID",
                value = "\"VersionCatalogSample\""
            )

            vectorDrawables {
                useSupportLibrary = true
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildToolsVersion = MpVersions.buildTools

        packaging {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
        }

    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            allWarningsAsErrors = false
        }
    }
}
