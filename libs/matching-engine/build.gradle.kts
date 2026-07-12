// libs:matching-engine — 순수 Kotlin 인메모리 매칭 엔진.
// ⚠️ Spring 의존 없음(의도). 나중에 별도 라이브러리로 publish 가능하도록 독립 유지.
plugins {
	kotlin("jvm")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

dependencies {
	// 테스트: kotlin-test(JUnit5 백엔드). 매칭 로직은 순수 함수라 mock 거의 불필요.
	testImplementation(kotlin("test"))
	// property-based test(1만 건 무작위 검증, 체크리스트 항목)를 하고 싶으면 아래 주석 해제:
	// testImplementation("io.kotest:kotest-property:5.9.1")
	// testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
