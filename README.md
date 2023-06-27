# VersionCatalogSample

This is a sample project that demonstrates how to manage dependencies using a version catalog and build logic in Gradle. The project showcases fetching data from a server using Retrofit and displaying it with Jetpack Compose.

## Version Catalog

A version catalog is a way to centralize and manage the versions of dependencies used in a project. Instead of hardcoding versions in multiple build files, a version catalog provides a single source of truth for dependency versions.

## Advantage of BuildSrc

The `buildSrc` directory allows you to write Kotlin code for build logic and plugins within your Gradle project. It provides a convenient way to organize and encapsulate custom logic, making it easier to maintain and share across multiple modules.

## Implementation Steps

1. Create a `lib.versions.toml` file:
   - In the root directory of your project, create a file called `lib.versions.toml`.
   - Specify the versions of your dependencies in TOML format within this file.

2. Create the `build-logic` module:
   - Create a new module called `build-logic` within your project.
   - Inside the `build-logic` module, create a `build.gradle.kts` file and configure the necessary plugins and dependencies.
   - Also, create a `settings.gradle.kts` file and configure the plugin management repositories and version catalogs.

3. Use the TOML file as dependencies in your project:
   - In your app module (or other modules where you want to use the dependencies), update the `build.gradle.kts` file.
   - Add the dependencies using the `libs` object, referencing the versions from the version catalog.

4. Create custom plugins:
   - Within the `convention` module, create Kotlin classes for custom plugins.
   - These plugins can be used to apply conventions, configurations, or additional dependencies.
   - For example, you can create a `AndroidLibraryComposeConventionPlugin` to add Jetpack Compose dependencies.

5. Apply the custom plugin:
   - In the app module's `build.gradle.kts` file, apply the custom plugin using the `id` or `implementationClass` provided in the plugin definition.

6. Build and run your project:
   - Build and run your project to verify that the dependencies are correctly resolved and the custom plugin is applied.

Feel free to explore the code and modify it according to your specific requirements.

