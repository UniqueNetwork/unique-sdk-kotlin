plugins {
    id("java")
    id("maven-publish")
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

val rustBasePath = project.ext.get("rust_base_path")

tasks.create("cargo-build", Exec::class.java) {
    dependsOn("cargo-output-dir")
    workingDir(rustBasePath!!)
    commandLine("cargo", "build", "--release")
}

//tasks.create("cargo-build-android-armv7", Exec::class.java) {
//    dependsOn("cargo-output-dir")
//    workingDir(rustBasePath!!)
//    commandLine("cross", "build", "--target", "armv7-linux-androideabi", "--release")
//}
//
//tasks.create("cargo-build-android-i686", Exec::class.java) {
//    dependsOn("cargo-output-dir")
//    workingDir(rustBasePath!!)
//    commandLine("cross", "build", "--target", "i686-linux-android", "--release")
//}
//
//tasks.create("cargo-build-android-aarch64", Exec::class.java) {
//    dependsOn("cargo-output-dir")
//    workingDir(rustBasePath!!)
//    commandLine("cross", "build", "--target", "aarch64-linux-android", "--release")
//}
//
//tasks.create("cargo-build-android-x86_64", Exec::class.java) {
//    dependsOn("cargo-output-dir")
//    workingDir(rustBasePath!!)
//    commandLine("cross", "build", "--target", "x86_64-linux-android", "--release")
//}

tasks.create("clean-rust", Delete::class.java) {
    delete("${project.ext.get("cargo_target_directory")}")
    delete("${rustBasePath}/Cargo.lock")
}

tasks["clean"].dependsOn("clean-rust")
tasks.withType(JavaCompile::class.java) {
    dependsOn("cargo-build")
}

//tasks.withType(Jar::class.java) {
//    dependsOn("cargo-build-android-armv7")
//    from("${project.ext.get("cargo_target_directory")}/armv7-linux-androideabi/release/libsigner.so") {
//        into("lib/armeabi-v7a/")
//    }
//}
//
//tasks.withType(Jar::class.java) {
//    dependsOn("cargo-build-android-i686")
//    from("${project.ext.get("cargo_target_directory")}/i686-linux-android/release/libsigner.so") {
//        into("lib/x86/")
//    }
//}
//
//tasks.withType(Jar::class.java) {
//    dependsOn("cargo-build-android-aarch64")
//    from("${project.ext.get("cargo_target_directory")}/aarch64-linux-android/release/libsigner.so") {
//        into("lib/arm64-v8a/")
//    }
//}
//
//tasks.withType(Jar::class.java) {
//    dependsOn("cargo-build-android-x86_64")
//    from("${project.ext.get("cargo_target_directory")}/x86_64-linux-android/release/libsigner.so") {
//        into("lib/x86_64/")
//    }
//}

tasks.test {
    useJUnitPlatform()
    systemProperty("java.library.path", "${project.ext.get("cargo_target_directory")}/release")
}

publishing {
    publications {
        register<MavenPublication>("java") {
            from(components["java"])
        }
    }
}