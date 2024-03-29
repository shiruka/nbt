import com.diffplug.spotless.LineEnding

plugins {
  java
  `maven-publish`
  signing
  alias(libs.plugins.spotless)
  alias(libs.plugins.nexus)
}

val signRequired = !rootProject.property("dev").toString().toBoolean()

group = "io.github.shiruka"

defaultTasks("build")

configurations {
  testImplementation.get().extendsFrom(compileOnly.get())
  testAnnotationProcessor.get().extendsFrom(annotationProcessor.get())
}

repositories {
  mavenCentral()
  maven("https://oss.sonatype.org/content/repositories/snapshots/")
  mavenLocal()
}

dependencies {
  compileOnly(libs.netty)
  compileOnly(libs.lombok)
  compileOnly(libs.annotations)

  testImplementation(libs.junit)

  annotationProcessor(libs.lombok)
}

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
  }
}

tasks {
  compileJava {
    options.encoding = Charsets.UTF_8.name()
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
    (options as StandardJavadocDocletOptions).tags("todo")
  }

  val javadocJar by creating(Jar::class) {
    dependsOn("javadoc")
    archiveClassifier.set("javadoc")
    from(javadoc)
  }

  val sourcesJar by creating(Jar::class) {
    dependsOn("classes")
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
  }

  build {
    dependsOn(jar)
    dependsOn(sourcesJar)
    dependsOn(javadocJar)
  }

  test {
    useJUnitPlatform()
  }
}

publishing {
  publications {
    val publication = create<MavenPublication>("mavenJava") {
      groupId = project.group.toString()
      artifactId = project.name
      version = project.version.toString()

      from(components["java"])
      artifact(tasks["sourcesJar"])
      artifact(tasks["javadocJar"])
      pom {
        name.set("Nbt")
        description.set("An Object-Oriented Nbt library.")
        url.set("https://shiruka.github.io/")
        licenses {
          license {
            name.set("MIT License")
            url.set("https://mit-license.org/license.txt")
          }
        }
        developers {
          developer {
            id.set("portlek")
            name.set("Hasan Demirtaş")
            email.set("utsukushihito@outlook.com")
          }
        }
        scm {
          connection.set("scm:git:git://github.com/shiruka/nbt.git")
          developerConnection.set("scm:git:ssh://github.com/shiruka/nbt.git")
          url.set("https://github.com/shiruka/nbt")
        }
      }
    }

    signing {
      isRequired = signRequired
      if (isRequired) {
        useGpgCmd()
        sign(publication)
      }
    }
  }
}

nexusPublishing.repositories.sonatype()

spotless {
  lineEndings = LineEnding.UNIX

  val prettierConfig =
    mapOf(
      "prettier" to "2.8.8",
      "prettier-plugin-java" to "2.2.0",
    )

  format("encoding") {
    target("*.*")
    encoding("UTF-8")
    endWithNewline()
    trimTrailingWhitespace()
  }

  yaml {
    target(
      ".github/**/*.yml",
      ".github/**/*.yaml",
    )
    endWithNewline()
    trimTrailingWhitespace()
    val jackson = jackson()
    jackson.yamlFeature("LITERAL_BLOCK_STYLE", true)
    jackson.yamlFeature("MINIMIZE_QUOTES", true)
    jackson.yamlFeature("SPLIT_LINES", false)
  }

  kotlinGradle {
    target("**/*.gradle.kts")
    indentWithSpaces(2)
    endWithNewline()
    trimTrailingWhitespace()
    ktlint()
  }

  java {
    target("**/src/**/java/**/*.java")
    importOrder()
    removeUnusedImports()
    indentWithSpaces(2)
    endWithNewline()
    trimTrailingWhitespace()
    prettier(prettierConfig)
      .config(
        mapOf("parser" to "java", "tabWidth" to 2, "useTabs" to false, "printWidth" to 100),
      )
  }
}
