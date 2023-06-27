# VersionCatalogSample

VersionCatalogSample is a sample project that showcases how to manage dependencies using a version catalog and build logic in Gradle. The project demonstrates fetching data from a server using Retrofit and displaying it with Jetpack Compose.

## Version Catalog

A version catalog is a mechanism for centralizing and managing dependency versions in a Gradle project. Instead of manually specifying version numbers in multiple build files, a version catalog provides a single source of truth for all dependencies. This approach simplifies maintenance, improves consistency, and enables easier updates across the project.

## Version Catalog vs BuildSrc in Modular Projects

In a modular project, two common approaches for managing dependencies and build logic are using a version catalog or `buildSrc`. Here are the advantages of using a version catalog:

- **Modularity:** With a version catalog, you can manage dependencies and versions separately from the build logic, allowing for better separation of concerns. This approach is particularly useful when working with multiple modules.

- **Decoupling from the IDE:** Version catalogs are independent of IDE integration. This decoupling improves build times, as IDEs don't need to recompile the build logic on every change.

- **Ease of sharing:** Version catalogs can be easily shared across different projects, making it simpler to maintain consistent dependency versions and build logic.

## Implementation Steps

To implement the version catalog and build logic in your project, follow these steps:

1. **Create the version catalog file:**
   - In the root directory of your project, create a file called `lib.versions.toml`.
   - Inside this file, list all your dependencies along with their respective versions using the TOML format.

2. **Create the `build-logic` module:**
   - Create a new module called `build-logic` within your project.
   - Inside the `build-logic` module, create a `build.gradle.kts` file and configure the necessary plugins and dependencies.
   - Also, create a `settings.gradle.kts` file in the `build-logic` module and configure the plugin management repositories and version catalogs.

3. **Use the version catalog in your project:**
   - In the build files of your modules (e.g., the app module), update the `build.gradle.kts` file.
   - Add the dependencies using the `libs` object, referencing the versions from the version catalog.

4. **Create custom plugins:**
   - Within the `convention` module, create Kotlin classes for your custom plugins.
   - These plugins can apply conventions, configurations, or additional dependencies to your modules.
   - For example, you can create an `AndroidLibraryComposeConventionPlugin` to add Jetpack Compose dependencies automatically.

5. **Apply the custom plugin:**
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

Please refer to the actual code in this repository for a more detailed implementation of each step.

## Defining New Plugins

To define new plugins, follow these guidelines:

1. Create a new Kotlin class for your plugin implementation.
2. Implement the `Plugin<Project>` interface and override the `apply` function.
3. Inside the `apply` function, use the `target` object to configure the

