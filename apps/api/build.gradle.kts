// apps:api 모듈 — HTTP API (WebFlux). 루트에서 '버전'만 확정한 플러그인을 여기서 '적용'한다.
plugins {
	kotlin("jvm")
	kotlin("plugin.spring")
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

dependencies {
	// WebFlux: 논블로킹 리액티브 웹 스택 (Netty 기반). Spring MVC(Tomcat) 아님.
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	// Kotlin data class <-> JSON 직렬화. 이게 없으면 기본 생성자 없는 Kotlin 클래스 역직렬화 실패.
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	// Reactor(Mono/Flux)를 Kotlin스럽게 쓰는 확장 함수.
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	// Spring이 런타임에 Kotlin 클래스 메타데이터를 읽기 위해 필요.
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// 핵심: suspend fun 컨트롤러 <-> Reactor 브리지. 코루틴을 WebFlux에 연결.
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		// JSR-305 null 어노테이션을 엄격 모드로 — Spring의 @Nullable 등을 Kotlin 타입 시스템이 신뢰.
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
