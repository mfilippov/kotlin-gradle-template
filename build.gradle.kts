plugins {
    kotlin("jvm") version "1.3.31"
}

buildscript {
    val kotlinVersion = "1.3.31"
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

apply(plugin = "kotlin")

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

repositories {
    mavenCentral()
}

tasks {
    withType<Jar> {
        manifest {
            attributes(mapOf(
                    "Main-Class" to "me.filippov.demo.AppKt"
            ))
        }
        from(sourceSets.main.get().output)
        dependsOn(configurations.runtimeClasspath)
        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}
