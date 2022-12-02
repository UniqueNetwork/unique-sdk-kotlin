val signEnabled: String by project
val signingKey: String by project
val signingPassword: String by project

plugins {
    id("java")
    id("maven-publish")
    id("signing")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

if (System.getenv("SIGN_ENABLED")?.toBoolean() ?: signEnabled.toBoolean()) {
    signing {
        useGpgCmd()
        sign(configurations.archives.get())
    }
}

val rustBasePath = project.ext.get("rust_base_path")

tasks.create("cargo-build", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
    commandLine("cargo", "build", "--release")
}

tasks.create("cargo-build-android-armv7", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
    commandLine("cross", "build", "--target", "armv7-linux-androideabi", "--release")
}

tasks.create("cargo-build-android-i686", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
    commandLine("cross", "build", "--target", "i686-linux-android", "--release")
}

tasks.create("cargo-build-android-aarch64", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
    commandLine("cross", "build", "--target", "aarch64-linux-android", "--release")
}

tasks.create("cargo-build-android-x86_64", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
    commandLine("cross", "build", "--target", "x86_64-linux-android", "--release")
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
    dependsOn("cargo-build-android-armv7")
    from("${project.ext.get("cargo_target_directory")}/armv7-linux-androideabi/release/libsigner.so") {
        into("lib/armeabi-v7a/")
    }
}

tasks.withType(Jar::class.java) {
    dependsOn("cargo-build-android-i686")
    from("${project.ext.get("cargo_target_directory")}/i686-linux-android/release/libsigner.so") {
        into("lib/x86/")
    }
}

tasks.withType(Jar::class.java) {
    dependsOn("cargo-build-android-aarch64")
    from("${project.ext.get("cargo_target_directory")}/aarch64-linux-android/release/libsigner.so") {
        into("lib/arm64-v8a/")
    }
}

tasks.withType(Jar::class.java) {
    dependsOn("cargo-build-android-x86_64")
    from("${project.ext.get("cargo_target_directory")}/x86_64-linux-android/release/libsigner.so") {
        into("lib/x86_64/")
    }
}

tasks.test {
    useJUnitPlatform()
    systemProperty("java.library.path", "${project.ext.get("cargo_target_directory")}/release")
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
        register<MavenPublication>("java") {
            groupId = "network.unique"
            artifactId = "unique-java-signer"
            version = project.version.toString()

            pom {
                name.set("Unique SDK Kotlin")
                description.set("SDK For Unique Network on Kotlin language")
                url.set("https://github.com/UniqueNetwork/unique-sdk-kotlin")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("stepan14041999")
                        name.set("Stepan Sandulyak")
                        email.set("stepan.14041999@gmail.com")
                    }
                }
                scm {
                    connection.set("https://github.com/UniqueNetwork/unique-sdk-kotlin.git")
                    developerConnection.set("https://github.com/UniqueNetwork/unique-sdk-kotlin.git")
                    url.set("https://github.com/UniqueNetwork/unique-sdk-kotlin")
                }
            }

            from(components["java"])
        }
    }
}