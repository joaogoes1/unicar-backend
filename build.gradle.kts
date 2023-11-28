plugins {
    id("java")
}

group = "com.unicar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.sparkjava:spark-core:2.7.2")
    implementation("com.google.inject:guice:7.0.0")
    implementation("com.auth0:java-jwt:4.4.0")
    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.slf4j:slf4j-simple:1.7.21")
    implementation("systems.manifold:manifold-ext-rt:2023.1.29")
    implementation(platform("com.google.cloud:libraries-bom:26.26.0"))
    implementation("com.google.cloud:google-cloud-firestore")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    annotationProcessor(group="systems.manifold", name="manifold-ext", version="2023.1.29")
}

tasks.test {
    useJUnitPlatform()
}

if (JavaVersion.current() != JavaVersion.VERSION_1_8 &&
    sourceSets.main.get().allJava.files.any { it.name == "module-info.java" }) {
    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xplugin:Manifold", "--module-path", classpath.asPath))
    }
} else {
    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xplugin:Manifold"))
    }
}