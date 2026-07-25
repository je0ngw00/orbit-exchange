package com.orbit.matching

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * 매칭(체결) 시나리오 테스트.
 * 아직 매칭 로직 미구현 → @Disabled. 다음 단계에서 내용 채우고 활성화(@Disabled 제거).
 */
class OrderBookMatchingTest {

    @Test
    @Disabled("매칭 로직 구현 후 활성화")
    fun `대기중인 SELL과 교차하는 BUY가 들어오면 완전 체결되고 책이 빈다`() {
        // given: 책에 SELL 0.5 @ 500만 대기
        //   TODO: OrderBook 생성 + SELL 주문 submit

        // when: BUY 0.5 @ 500만 도착
        //   TODO: BUY 주문 submit → 결과 받기

        // then: Trade 1건(0.5 @ 500만), 둘 다 완전 체결, 책은 빔
        //   TODO: 결과가 Success 이고 trades 1건인지, bestBid/bestAsk 가 null 인지 검증
    }
}
