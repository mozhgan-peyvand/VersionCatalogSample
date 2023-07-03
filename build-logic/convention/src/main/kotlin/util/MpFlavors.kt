package util

import com.android.build.api.dsl.ProductFlavor
import com.android.build.gradle.BaseExtension

data class MpFlavor(
    val name: String,
    val dimension: String,
    val buildConfigs: List<BuildConfigField> = listOf()
)

data class BuildConfigField(
    val type: String,
    val name: String,
    val value: String
)

fun BaseExtension.configureFlavor(
    flavors: List<MpFlavor>,
    flavorConfigurationBlock: ProductFlavor.(flavor: MpFlavor) -> Unit = {}
) {
    productFlavors {
        flavors.forEach {
            create(it.name) {
                flavorConfigurationBlock(it)
                dimension = it.dimension
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
