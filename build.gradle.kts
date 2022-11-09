import java.io.ByteArrayOutputStream;

val ktorVersion: String by project
val kotlinxCoroutinesVersion: String by project

group = "who.we"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("multiplatform") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("maven-publish")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://dl.bintray.com/emerald/polkaj") }
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        withJava()

        // Append to `lib/build.gradle`
        val rustBasePath = "./src/signer"

        // execute cargo metadata and get path to target directory
        tasks.create("cargo-output-dir") {
            val os = ByteArrayOutputStream()
            exec {
                commandLine("cargo", "metadata", "--format-version", "1")
                workingDir(rustBasePath)
                standardOutput = os
            }
            val outputAsString = os.toString()
            val json = groovy.json.JsonSlurper().parseText(outputAsString) as Map<String, String>

            logger.info("cargo target directory: ${json["target_directory"]}")
            project.ext.set("cargo_target_directory", json["target_directory"])
        }

        // Build with cargo
        tasks.create("cargo-build", Exec::class.java) {
            dependsOn("cargo-output-dir")
            workingDir(rustBasePath)
            commandLine("cargo", "build", "--release")

            doLast {
                exec {
                    workingDir(rustBasePath)
                    commandLine("cross", "build", "--target", "armv7-linux-androideabi", "--release")
                }
                exec {
                    workingDir(rustBasePath)
                    commandLine("cross", "build", "--target", "i686-linux-android", "--release")
                }
                exec {
                    workingDir(rustBasePath)
                    commandLine("cross", "build", "--target", "aarch64-linux-android", "--release")
                }
                exec {
                    workingDir(rustBasePath)
                    commandLine("cross", "build", "--target", "x86_64-linux-android", "--release")
                }
            }
        }

        tasks.create("clean-rust", Delete::class.java) {
            delete("${project.ext.get("cargo_target_directory")}")
            delete("${rustBasePath}/Cargo.lock")
        }

        tasks["clean"].dependsOn("clean-rust")
        tasks.withType(JavaCompile::class.java) {
                dependsOn("cargo-build")
        }

        tasks.withType(Jar::class.java) {
            from("${project.ext.get("cargo_target_directory")}/armv7-linux-androideabi/release/libsigner.so")
            into("natives/armv7/")
        }

        tasks.withType(Jar::class.java) {
            from("${project.ext.get("cargo_target_directory")}/i686-linux-android/release/libsigner.so")
            into("natives/i686/")
        }

        tasks.withType(Jar::class.java) {
            from("${project.ext.get("cargo_target_directory")}/aarch64-linux-android/release/libsigner.so")
            into("natives/aarch64/")
        }

        tasks.withType(Jar::class.java) {
            from("${project.ext.get("cargo_target_directory")}/x86_64-linux-android/release/libsigner.so")
            into("natives/x86_64/")
        }

        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
            systemProperty("java.library.path", "${project.ext.get("cargo_target_directory")}/release")
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
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
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.emeraldpay.polkaj:polkaj-tx:0.5.0-SNAPSHOT")
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
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
