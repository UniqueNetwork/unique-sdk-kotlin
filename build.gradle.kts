group = "who.we"
version = "1.0-SNAPSHOT"

allprojects {
    // Append to `lib/build.gradle`
    val rustBasePath = "${project.rootDir}/java-signer/src/signer"
    project.ext.set("rust_base_path", rustBasePath)

    // execute cargo metadata and get path to target directory
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