import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

private val mpBuildTypes = listOf(
    CupBuildType(
        name = "debug",
        buildConfigs = listOf(
            BuildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://quotable.io/\""
            )
        )
    ),
    CupBuildType(
        name = "release",
        buildConfigs = listOf(
            BuildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://quotable.io/\""
            )
        )
    ),
    CupBuildType(
        name = "staging",
        buildConfigs = listOf(
            BuildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://quotable.io/\""
            )
        )
    )
)

class AndroidBuildTypesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseExtension> {
                configureBuildType(
                    baseExtension = this,
                    buildTypes = mpBuildTypes
                )
            }
        }
    }
}