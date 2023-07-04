# VersionCatalogSample

VersionCatalogSample is a sample project that showcases how to manage dependencies using a version catalog and build logic in Gradle. The project demonstrates fetching data from a server using Retrofit and displaying it with Jetpack Compose.

## Version Catalog

A version catalog is a mechanism for centralizing and managing dependency versions in a Gradle project. Instead of manually specifying version numbers in multiple build files, a version catalog provides a single source of truth for all dependencies. This approach simplifies maintenance, improves consistency, and enables easier updates across the project.

## Version Catalog vs BuildSrc in Modular Projects

In a modular project, two common approaches for managing dependencies and build logic are using a version catalog or `buildSrc`. Here are the advantages of using a version catalog:

- **Modularity:** In a modular project, it's important to separate concerns and manage dependencies and versions separately from the build logic. With a version catalog, you can define and manage your dependencies in a centralized file, keeping them decoupled from the individual build scripts of each module. This modular approach makes it easier to understand and maintain the project structure, especially when dealing with multiple modules.

- **Decoupling from the IDE:** When using `buildSrc`, the build logic resides within the project itself and is tightly integrated with the IDE. This means that any changes to the build logic trigger a recompilation within the IDE, which can impact build times, especially in larger projects. In contrast, version catalogs are independent of IDE integration. Changes to the version catalog don't require recompilation within the IDE, resulting in faster build times and a smoother development experience.

- **Ease of sharing:** Version catalogs can be easily shared across different projects, making it simpler to maintain consistent dependency versions and build logic.

## Implementation Steps

To implement the version catalog and build logic in your project, follow these steps:

1. **Create the version catalog file:**
   - In the directory ../gradle/, create a file called libs.versions.toml.
   - Inside this file, list all your dependencies along with their respective versions using the TOML format.
   ```groovy
   [versions]
   retrofit = "2.9.0"
   retrofitMoshiConverter = "0.8.0"
   moshi = "1.14.0"
   moshiCodegen = "1.14.0"

   [libraries]
   retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
   retrofitMoshiConverter = { module = "com.squareup.retrofit2:converter-moshi", version.ref = "retrofit" }
   moshi = { module = "com.squareup.moshi:moshi", version.ref = "moshi"}
   moshiCodegen = { module = "com.squareup.moshi:moshi-kotlin-codegen", version.ref = "moshiCodegen"}

2. **Create the `build-logic` module:**
   - Create a new module called `build-logic` within your project.
   - Also, create a `settings.gradle.kts` file in the `build-logic` module and configure the plugin management repositories and version catalogs like that:

   ```groovy
   pluginManagement {
        repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
     }
   }
   dependencyResolutionManagement {
    repositories {
            google()
        mavenCentral()
     }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
      }
   }
   rootProject.name = "build-logic"

  The `build-logic` module can have various forms, depending on the requirements of your project. It typically contains the following:

  - Version Catalogs: The build-logic module can include version catalogs, which are files (e.g., TOML format) that centralize the management of dependency versions. Version catalogs help ensure that all modules within 
    the project use consistent and compatible versions of dependencies, making it easier to manage and update dependencies across the project.
    
  - Custom Plugins: You can define custom Gradle plugins in the build-logic module to encapsulate complex build logic, tasks, and configurations specific to your project. These plugins can be applied to different 
    modules within the project to provide consistent and reusable build functionalities.
 
  - Common Build Configurations: The build-logic module can define common build configurations that are shared across multiple modules. This includes settings such as compiler versions, code quality checks, code 
    generation, and more. By centralizing these configurations in the build-logic module, you can maintain consistency and reduce duplication across modules.

    a separate build-logic module offers modularity, reusability, scalability, and flexibility in managing complex build scenarios. It allows for the isolation of build-related configurations, custom plugins, 
   and version catalogs, leading to a more organized and maintainable project structure.

    then we should add this module in setting.gradle of project like that:
   
    ```groovy
    pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
      }
     }

3. **Use the version catalog in your project:**
   - In the build files of your modules (e.g., the app module), update the `build.gradle(.kts)` file.
   - Add the dependencies using the `libs` object, referencing the versions from the version catalog.
   - build.gradle:
       ```groovy
            dependencies {
              implementation libs.retrofit
              implementation libs.retrofitMoshiConverter

              implementation libs.moshi
              kapt libs.moshiCodegen
            }
   
   - build.gradle.kts:
      ```groovy
           dependencies {
            implementation(libs.retrofit)
            implementation(libs.retrofitMoshiConverter)

            implementation(libs.moshi)
            kapt(libs.moshiCodegen)
           }

## Defining New Plugins

1. **Create custom plugins:**
   - Within the `convention` module, create Kotlin classes for your custom plugins.
   - These plugins can apply conventions, configurations, or additional dependencies to your modules.
   - For example, you can create an `AndroidLibraryComposeConventionPlugin` to add Jetpack Compose dependencies automatically.

2. **Apply the custom plugin:**
   - In the build files of your modules (e.g., the app module), apply the custom plugin using the `id` or `implementationClass` provided in the plugin definition.
   ```groovy
   plugins {
       id 'com.android.application'
       id 'org.jetbrains.kotlin.android'
       id 'kotlin-kapt'
       id 'mp.android.library.compose'
   }

6. **Building and Running the Project:**
   - Build and run your project to verify that the dependencies are correctly resolved, and the custom plugin is applied.
## Defining New Plugins
  Custom plugins in Android development can be extremely helpful for managing complex projects and streamlining repetitive tasks. Here are a few use cases where custom plugins can be beneficial:

   - Build Variants or Build Types: Custom plugins allow you to define and configure specific build variants or build types that are unique to your project. For example, you can create a plugin that sets up different configurations for debug, release, or staging builds. This helps in maintaining consistent and efficient build configurations across multiple modules.

   - Dependency Management: If your project has a set of common dependencies that are used across multiple modules, you can create a custom plugin to handle their configuration. This avoids duplication and ensures consistency in dependency versions and settings throughout your project.

   - Code Generation: Custom plugins can automate code generation tasks. For instance, you can create a plugin that generates boilerplate code or initializes specific components based on predefined templates. This saves development time and reduces the chances of errors caused by manual code generation.

   - Custom Conventions: Plugins can enforce custom conventions or best practices in your project. For example, you can create a plugin that enforces specific coding standards, project structure, or naming conventions. This helps maintain consistency and improves the overall code quality across the project.

1. **Create custom plugins:** 
   - First, create a new module named `convention` in your build-logic module.
   - then add it as an module to setting.gradle(.kts) of build-logic module near rootname: 
     
     ```kotlin
     rootProject.name = "build-logic"
     include(":convention")
     
   - Inside the `convention` module, define a `build.gradle.kts` file and apply the Kotlin DSL plugin. add the required plugins and dependencies. Here's an example configuration:
     ```kotlin
     plugins {
         `kotlin-dsl`
     }

     java {
         sourceCompatibility = JavaVersion.VERSION_17
         targetCompatibility = JavaVersion.VERSION_17
     }

     dependencies {
         compileOnly(libs.gradleAndroid)
         compileOnly(libs.kotlinGradlePlugin)
     }

     gradlePlugin {
     
         plugins {
             register("androidLibraryCompose") {
                 id = "mp.android.library.compose"
                 implementationClass = "AndroidLibraryComposeConventionPlugin"
             }
         }
     }
     ```
     **Plugins:** In the Gradle build system, plugins are used to extend the functionality of the build process. They provide pre-defined tasks, configurations, and conventions that can be applied to a project. In the example you provided, the `kotlin-dsl` plugin is applied, which enables the use of Kotlin DSL syntax in the build.gradle.kts files.

     **Java Compatibility:** The java block is used to configure the Java compatibility settings for the project. In the example, it sets the source and target compatibility to Java version 17. This ensures that the project can be compiled and executed using Java 17 features.

     **Dependencies:** The dependencies block is used to declare the dependencies required by the project. In the example, the `compileOnly` configuration is used to include Gradle Android and Kotlin Gradle Plugin dependencies. These dependencies are necessary for the custom plugin to function correctly.

     **gradlePlugin:** The gradlePlugin block is used to define a custom Gradle plugin. It allows you to register and configure a plugin with a specific ID and implementation class. In the example, the custom plugin is registered with the ID `androidLibraryCompose` and the implementation class `AndroidLibraryComposeConventionPlugin`.
   - Create a Kotlin class, such as `AndroidLibraryComposeConventionPlugin`, to define your custom plugin. Here's an example implementation:

     ```kotlin
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
     ```
     - It is important to note that the directory structure should be set up as follows:
       
     ```kotlin
     ├── app
     │   └── build.gradle
     ├── build-logic
     │   ├── convention
     │   │   ├── build.gradle
     │   │   └── src
     │   │       └── main
     │   │           └── kotlin
     │   │               └── CommonPluginClass.kt
     │   └── settings.gradle
     └── settings.gradle

     ```
- Apply the custom plugin:

   - In the build files of your modules (e.g., the app module), apply the custom plugin using the provided plugin ID. Here's an example:

     ```kotlin
     plugins {
         id 'com.android.application'
         id 'org.jetbrains.kotlin.android'
         id 'kotlin-kapt'
         id 'mp.android.library.compose'
     }
     ```

   - Make sure to include the necessary plugins for your project, such as the Android application plugin and the Kotlin Android plugin.

   - By applying the `mp.android.library.compose` plugin, your module will automatically incorporate the specified Jetpack Compose dependencies defined in your custom plugin.

   - Feel free to customize the plugin implementation according to your project requirements and add more custom plugins as needed.

Please refer to the actual code in this repository for a more detailed implementation of each step.


