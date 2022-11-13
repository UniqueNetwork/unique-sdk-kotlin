pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.google.cloud.tools.appengine")) {
                useModule("com.google.cloud.tools:appengine-gradle-plugin:${requested.version}")
            } else if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:7.2.0")
            } else if (requested.id.id == "com.squareup.sqldelight"){
                useModule("com.squareup.sqldelight:gradle-plugin:1.4.4")
            }
        }
    }
}

rootProject.name = "unique-sdk-kotlin"
include("kotlin-multiplatform")
include("java-signer")
