buildscript {
    val kotlinVersion: String by project
    val kotlinterGradle: String by project
    val gradleVersionsPlugin: String by project

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(uri("https://plugins.gradle.org/m2/")) // For kotlinter-gradle
    }

    dependencies {
        // keeping this here to allow AS to automatically update
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
        classpath("org.jmailen.gradle:kotlinter-gradle:${kotlinterGradle}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${gradleVersionsPlugin}")
    }
}

val ktorVersion: String by project
val composeCompiler: String by project
val kotlinxCoroutinesVersion: String by project

group = "who.we"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("multiplatform") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("com.android.library")
    id("maven-publish")
}

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://dl.bintray.com/emerald/polkaj") }
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
}

configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(module("org.jetbrains.compose.compiler:compiler")).apply {
            using(module("androidx.compose.compiler:compiler:${composeCompiler}"))
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    namespace = "com.surrus.common"
}

kotlin {
    android()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }

        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
            systemProperty("java.library.path", "${project.ext.get("cargo_target_directory")}/release")
        }
    }


    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(project(":java-signer"))
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("com.marcinziolo:kotlin-wiremock:2.0.1")
                implementation(kotlin("test"))
                implementation("ch.qos.logback:logback-classic:1.2.3")
                implementation("io.ktor:ktor-client-apache:$ktorVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.annotation:annotation:1.5.0")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
    }
}
