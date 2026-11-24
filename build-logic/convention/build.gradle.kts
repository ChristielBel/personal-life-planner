plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("codeStyle") {
            id = "app.codestyle"
            implementationClass = "CodeStyleConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.detekt.gradle.plugin)
}