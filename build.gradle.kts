plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.10"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "projects"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Database
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")

	implementation("com.github.f4b6a3:uuid-creator:6.1.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

	// Testcontainers
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.testcontainers:testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")

	// Cucumber
	testImplementation("io.cucumber:cucumber-java:7.20.1")
	testImplementation("io.cucumber:cucumber-spring:7.20.1")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:7.20.1")

	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.5.0")
	// Suite para rodar via JUnit 5
	testImplementation("org.junit.platform:junit-platform-suite")
	testImplementation("net.java.dev.jna:jna:5.16.0")

	//observabilidade
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
