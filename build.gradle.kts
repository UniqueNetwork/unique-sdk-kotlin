plugins {
    id("maven-publish")
}

allprojects {
    group = "network.unique"
    version = "0.0.2"

    val rustBasePath = "${project.rootDir}/unique-sdk-java-signer/src/signer"
    project.ext.set("rust_base_path", rustBasePath)

    tasks.create("cargo-output-dir") {
        val os = java.io.ByteArrayOutputStream()
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
        register<MavenPublication>("maven") {
            pom {
                name.set("Unique SDK POM")
                description.set("SDK POM For Unique Network")
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
        }
    }
}