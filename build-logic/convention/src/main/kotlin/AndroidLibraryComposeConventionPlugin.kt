import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findLibrary("composeCompiler").get())
                "implementation"(libs.findLibrary("composeFoundation").get())
                "implementation"(libs.findLibrary("composeMaterial").get())
                "implementation"(libs.findLibrary("composeRuntime").get())
                "implementation"(libs.findLibrary("composeUiTooling").get())
                "implementation"(libs.findLibrary("composeUi").get())
                "implementation"(libs.findLibrary("activityCompose").get())
                "implementation"(libs.findLibrary("composeUiGraphics").get())
            }
        }
    }
}
