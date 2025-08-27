plugins {
    id("java")
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "2.1.20"
    id("org.jetbrains.kotlin.plugin.lombok") version "2.1.20"
    id("io.freefair.lombok") version "8.13.1"
    `maven-publish`
}

group = "colosseum.minecraft"
version = "0.1-SNAPSHOT"

base {
    archivesName.set(project.name)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
    exclusiveContent {
        forRepository {
            maven("https://coffeewarehouse.harborbucket.top/snapshots")
        }
        filter {
            includeGroup("colosseum.minecraft")
            includeGroup("net.md-5")
        }
    }
}

dependencies {
    compileOnly("colosseum.minecraft:colosseumspigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("colosseum.minecraft:colosseumspigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("org.projectlombok:lombok:${rootProject.findProperty("lombok_version")}")
    annotationProcessor("org.projectlombok:lombok:${rootProject.findProperty("lombok_version")}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${rootProject.findProperty("junit_version")}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${rootProject.findProperty("junit_version")}")
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 17
    options.encoding = "UTF-8"
}

tasks.jar {
    archiveClassifier.set("original")
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(
            "name" to project.name,
            "version" to project.version
        )
    }
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }

    System.getenv("COLOSSEUM_MAVEN_URL").let { mavenUrl ->
        if (mavenUrl == null) {
            return@let
        }
        println("Configuring publishing")
        repositories {
            maven {
                name = "ColosseumMaven"
                url = uri(mavenUrl)
                credentials {
                    username = System.getenv("COLOSSEUM_MAVEN_USERNAME")
                    password = System.getenv("COLOSSEUM_MAVEN_PASSWORD")
                }
            }
        }
    }
}