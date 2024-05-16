import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacocoExclusions = arrayOf(
	"com/group76/catalog/mapping/**",
)

plugins {
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
	jacoco
}

group = "com.group76"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.retry:spring-retry")
	implementation("org.springframework.boot:spring-boot-starter-web"){
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	implementation("org.springframework.boot:spring-boot-starter-undertow")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
	}
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:4.1.1") {
		exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
		exclude(group = "org.bouncycastle", module = "bcprov-jdk18on")
		exclude(group = "commons-fileupload", module = "commons-fileupload")
	}
	implementation("io.github.openfeign:feign-okhttp:13.2.1")
	implementation("software.amazon.awssdk:ssm:2.25.53")

	testImplementation("io.mockk:mockk:1.13.10")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("org.mockito.kotlin:mockito-kotlin:5.3.1")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

tasks.withType<JacocoReport> {
	reports {
		xml.required.set(true)
	}

	afterEvaluate {
		classDirectories.setFrom(files(classDirectories.files.map {
			fileTree(it).apply {
				exclude(*jacocoExclusions)
			}
		}))
	}

	finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
}

tasks.withType<JacocoCoverageVerification> {
	dependsOn(tasks.jacocoTestReport)

	violationRules {
		isFailOnViolation = false
		rule {
			limit {
				minimum = BigDecimal(0.8)
			}
		}
	}

	afterEvaluate {
		classDirectories.setFrom(files(classDirectories.files.map {
			fileTree(it).apply {
				exclude(*jacocoExclusions)
			}
		}))
	}
}
