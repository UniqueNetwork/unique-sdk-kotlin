import java.io.ByteArrayOutputStream;

plugins {
    id("java")
}

group = "who.we"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

val rustBasePath = project.ext.get("rust_base_path")

// Build with cargo
tasks.create("cargo-build", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
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
    from("${project.ext.get("cargo_target_directory")}/armv7-linux-androideabi/release/libsigner.so") {
        into("natives/armv7/")
    }
}

tasks.withType(Jar::class.java) {
    from("${project.ext.get("cargo_target_directory")}/i686-linux-android/release/libsigner.so") {
        into("natives/i686/")
    }
}

tasks.withType(Jar::class.java) {
    from("${project.ext.get("cargo_target_directory")}/aarch64-linux-android/release/libsigner.so") {
        into("natives/aarch64/")
    }
}

tasks.withType(Jar::class.java) {
    from("${project.ext.get("cargo_target_directory")}/x86_64-linux-android/release/libsigner.so") {
        into("natives/x86_64/")
    }
}

tasks.test {
    useJUnitPlatform()
    systemProperty("java.library.path", "${project.ext.get("cargo_target_directory")}/release")
}