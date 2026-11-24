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