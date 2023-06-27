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
                value = "\"https://dev.icup.ir:1364/\""
            ),
            BuildConfigField(
                type = "String",
                name = "FARASHENASA_URL",
                value = "\"https://dev-partpay.farashenasa.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_URL",
                value = "\"https://barjavand-v3-dev.apipart.ir/\""
            ),
            BuildConfigField(
                type = "String",
                name = "SIGNATURE_URL",
                value = "\"cup-dev.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_HOST_URL",
                value = "\"barjavand-v3-dev.partpay.apipart.ir\""
            )
        )
    ),
    CupBuildType(
        name = "release",
        buildConfigs = listOf(
            BuildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://app.icup.ir/\""
            ),
            BuildConfigField(
                type = "String",
                name = "FARASHENASA_URL",
                value = "\"https://partpay.farashenasa.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_URL",
                value = "\"https://barjavand-v3.gw.icup.ir/\""
            ),
            BuildConfigField(
                type = "String",
                name = "SIGNATURE_URL",
                value = "\"cup.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_HOST_URL",
                value = "\"\""
            )
        )
    ),
    CupBuildType(
        name = "staging",
        buildConfigs = listOf(
            BuildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://test.icup.ir:1364/\""
            ),
            BuildConfigField(
                type = "String",
                name = "FARASHENASA_URL",
                value = "\"https://test-partpay.farashenasa.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_URL",
                value = "\"http://192.168.4.135/\""
            ),
            BuildConfigField(
                type = "String",
                name = "SIGNATURE_URL",
                value = "\"cup-test.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_HOST_URL",
                value = "\"barjavand-v3-test.partpay.apipart.ir\""
            )
        )
    ),
    CupBuildType(
        name = "drp",
        buildConfigs = listOf(
            BuildConfigField(
                type = "String",
                name = "SERVER_URL",
                value = "\"https://app-test.icup.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "FARASHENASA_URL",
                value = "\"https://partpay.farashenasa.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_URL",
                value = "\"https://barjavand-v3-drp.gw.icup.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "SIGNATURE_URL",
                value = "\"cup.ir\""
            ),
            BuildConfigField(
                type = "String",
                name = "BARJAVAND_HOST_URL",
                value = "\"barjavand-v3.gw.icup.ir\""
            ),
            BuildConfigField(
                type = "boolean",
                name = "DEBUG",
                value = "true"
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