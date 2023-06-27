import com.android.build.api.dsl.BuildType
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

data class CupBuildType(
    val name: String,
    val buildConfigs: List<BuildConfigField> = listOf()
)

fun Project.configureBuildType(
    baseExtension: BaseExtension,
    buildTypes: List<CupBuildType>,
    buildTypeConfigurationBlock: BuildType.(buildType: CupBuildType) -> Unit = {}
) {
    baseExtension.buildTypes {
        buildTypes.forEach {
            maybeCreate(it.name)
            getByName(it.name) {
                buildTypeConfigurationBlock(it)
                it.buildConfigs.forEach { field ->
                    buildConfigField(
                        type = field.type,
                        name = field.name,
                        value = field.value
                    )
                }
            }
        }
    }
}

data class BuildConfigField(
    val type: String,
    val name: String,
    val value: String
)