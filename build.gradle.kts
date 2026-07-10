// 루트 build.gradle.kts — aggregator(집약) 모듈.
// 여기서는 코드를 두지 않고, 하위 모듈들이 공유할 플러그인 '버전'과 공통 설정만 선언한다.
// `apply false` = 버전만 확정하고, 실제 적용은 각 하위 모듈(build.gradle.kts)에서 한다.
plugins {
	kotlin("jvm") version "2.1.20" apply false
	kotlin("plugin.spring") version "2.1.20" apply false
	id("org.springframework.boot") version "3.5.16" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
	group = "com.orbit"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}
