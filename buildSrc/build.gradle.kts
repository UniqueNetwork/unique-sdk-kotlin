dependencies {
    implementation("org.jetbrains.dokka:dokka-core:1.6.21")
}

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://dl.bintray.com/emerald/polkaj") }
    maven { url = uri("https://jitpack.io") }
    mavenLocal()
}
