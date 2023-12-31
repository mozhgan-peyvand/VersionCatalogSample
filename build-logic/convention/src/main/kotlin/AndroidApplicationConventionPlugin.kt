import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import util.MpVersions
import util.MpVersions.targetSdk
import util.configureKotlinAndroid

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("mp.android.buildTypes")
                apply("org.jetbrains.kotlin.android")
                apply("mp.android.source.flavors")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = targetSdk
            }
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)


            extensions.configure<AppExtension> {
                applicationVariants.all {
                    val variant = this
                    variant.outputs
                        .map { it as BaseVariantOutputImpl }
                        .forEach { output ->
                            val outputFileName =
                                "Mp-${variant.buildType.name}-${variant.flavorName}-v${MpVersions.versionCode}.apk"
                            output.outputFileName = outputFileName
                        }
                }
            }
            println("APK version code: " + MpVersions.versionCode)

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                "implementation"(project(":sample-library"))
                "implementation"(project(":base"))

                "implementation"(libs.findLibrary("composeCompiler").get())
                "implementation"(libs.findLibrary("composeFoundation").get())
                "implementation"(libs.findLibrary("composeMaterial").get())
                "implementation"(libs.findLibrary("composeRuntime").get())
                "implementation"(libs.findLibrary("composeUiTooling").get())
                "implementation"(libs.findLibrary("composeUi").get())
                "implementation"(libs.findLibrary("activityCompose").get())
                "implementation"(libs.findLibrary("composeUiGraphics").get())
                "implementation"(libs.findLibrary("coreKtx").get())
                "implementation"(libs.findLibrary("lifecycleRuntime").get())
            }
        }
    }
}
