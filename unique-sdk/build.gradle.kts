buildscript {
    val kotlinVersion: String by project
    val kotlinterGradle: String by project
    val gradleVersionsPlugin: String by project

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(uri("https://plugins.gradle.org/m2/"))
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath("com.google.guava:guava:31.1-jre")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
        classpath("org.jmailen.gradle:kotlinter-gradle:${kotlinterGradle}")
        classpath("com.github.ben-manes:gradle-versions-plugin:${gradleVersionsPlugin}")
    }
}

val ktorVersion: String by project
val composeCompiler: String by project
val kotlinxCoroutinesVersion: String by project
val signEnabled: String by project
val signingKey: String by project
val signingPassword: String by project

group = "network.unique"
version = "0.0.3"

plugins {
    kotlin("multiplatform") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("com.android.library")
    id("maven-publish")
    id("signing")
//    id("org.openapi.generator") version "6.2.1"
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

//openApiGenerate {
//    generatorName.set("kotlin")
//    inputSpec.set("$projectDir/specs/opal-spec.yml")
//    outputDir.set("$projectDir/src/openApiGenerator")
//    apiPackage.set("network.unique.api")
//    invokerPackage.set("network.unique.invoker")
//    modelPackage.set("network.unique.model")
//    skipValidateSpec.set(true)
//}
//
//tasks.create("downloadSpec", org.jetbrains.kotlin.de.undercouch.gradle.tasks.download.Download::class.java) {
//    src("https://rest.opal.uniquenetwork.dev/swagger-yaml")
//    dest(File(projectDir, "specs/opal-spec.yml"))
//}
//
//tasks.openApiGenerate {
//    dependsOn("downloadSpec")
//}

android {
    compileSdk = 23
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        aarMetadata {
            minCompileSdk = 23
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    namespace = "network.unique"
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
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

    if (System.getenv("SIGN_ENABLED")?.toBoolean() ?: signEnabled.toBoolean()) {
        signing {
            useGpgCmd()
        }
    }

    sourceSets {
        val openApiGenerator by creating {
            kotlin.srcDir("src/openApiGenerator/src/main/kotlin")
        }
        val jvmMain by getting {
            dependsOn(openApiGenerator)

            dependencies {
                implementation(project(":unique-sdk-java-signer"))
                implementation("com.squareup.okhttp3:okhttp:4.10.0")
                implementation("com.squareup.moshi:moshi:1.14.0")
                implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("com.marcinziolo:kotlin-wiremock:2.0.1")
                implementation(kotlin("test"))
                implementation("ch.qos.logback:logback-classic:1.2.3")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.annotation:annotation:1.5.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            name = "OSSRH"
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials(PasswordCredentials::class)
        }
    }
    publications {
        withType<MavenPublication>() {
            if (this.name == "jvm") {
                // Stub javadoc.jar artifact
                artifact(javadocJar.get())

                pom {
                    name.set("Unique SDK Kotlin")
                    description.set("SDK For Unique Network with Key Storage for Android")
                    url.set("https://github.com/UniqueNetwork/unique-sdk-kotlin")
                    licenses {
                        license {
                            name.set("MIT")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    developers {
                        developer {
                            id.set("Nikolai Pasynkov")
                            name.set("Nikolai Pasynkov")
                            email.set("np@unique.network")
                        }
                    }
                    scm {
                        connection.set("https://github.com/UniqueNetwork/unique-sdk-kotlin.git")
                        developerConnection.set("https://github.com/UniqueNetwork/unique-sdk-kotlin.git")
                        url.set("https://github.com/UniqueNetwork/unique-sdk-kotlin")
                    }
                }

                if (System.getenv("SIGN_ENABLED")?.toBoolean() ?: signEnabled.toBoolean()) {
                    the<SigningExtension>().sign(this)
                }
            }
        }
        register<MavenPublication>("release") {
            artifact(javadocJar)
            groupId = "network.unique"
            artifactId = "unique-sdk-android"
            version = project.version.toString()

            pom {
                name.set("Unique SDK Android")
                description.set("SDK For Unique Network on Kotlin language")
                url.set("https://github.com/UniqueNetwork/unique-sdk-kotlin")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("Nikolai Pasynkov")
                        name.set("Nikolai Pasynkov")
                        email.set("np@unique.network")
                    }
                }
                scm {
                    connection.set("https://github.com/UniqueNetwork/unique-sdk-kotlin.git")
                    developerConnection.set("https://github.com/UniqueNetwork/unique-sdk-kotlin.git")
                    url.set("https://github.com/UniqueNetwork/unique-sdk-kotlin")
                }
            }

            if (System.getenv("SIGN_ENABLED")?.toBoolean() ?: signEnabled.toBoolean()) {
                the<SigningExtension>().sign(this)
            }

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
