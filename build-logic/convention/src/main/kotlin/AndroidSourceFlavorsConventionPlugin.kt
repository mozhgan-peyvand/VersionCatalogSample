import com.android.build.gradle.BaseExtension
import util.MpFlavor
import util.configureFlavor

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

private val sourceFlavors = listOf(
    MpFlavor(
        name = "live",
        dimension = "source"
    ),
    MpFlavor(
        name = "mock",
        dimension = "source"
    )
)

class AndroidSourceFlavorsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseExtension> {
                flavorDimensionList += "source"
                configureFlavor(sourceFlavors)
            }
        }
    }
}
