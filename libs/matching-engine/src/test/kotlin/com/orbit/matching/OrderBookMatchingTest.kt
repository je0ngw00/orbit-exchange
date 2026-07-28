package com.orbit.matching

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * 매칭(체결) 시나리오 테스트.
 * 아직 매칭 로직 미구현 → @Disabled. 다음 단계에서 내용 채우고 활성화(@Disabled 제거).
 */
class OrderBookMatchingTest {

    @Test
    fun `대기중인 SELL과 교차하는 BUY가 들어오면 완전 체결되고 책이 빈다`() {
        // given: 책에 SELL 0.5 @ 500만 대기
        val orderBook = OrderBook()
        val sell = Order(
            id = 1L,
            side = Side.SELL,
            type = OrderType.LIMIT,
            price = BigDecimal.valueOf(5_000_000L),
            quantity = BigDecimal.valueOf(1L),
            remaining = BigDecimal.valueOf(1L)
        )
        orderBook.submit(sell)

        // when: BUY 0.5 @ 500만 도착
        val buy = Order(
            id = 2L,
            side = Side.BUY,
            type = OrderType.LIMIT,
            price = BigDecimal.valueOf(5_000_000L),
            quantity = BigDecimal.valueOf(1L),
            remaining = BigDecimal.valueOf(1L)
        )
        val actual = orderBook.submit(buy)

        // then: Trade 1건(0.5 @ 500만), 둘 다 완전 체결, 책은 빔
        assertTrue(actual is MatchResult.Success)
        val success = actual as MatchResult.Success
        assertEquals(1, success.trades.size)
        assertEquals(0, success.trades[0].price.compareTo(BigDecimal.valueOf(5_000_000L)))
        assertNull(success.remainingOrder)
        assertNull(orderBook.bestBid())
        assertNull(orderBook.bestAsk())
    }

    @Test
    fun `같은 가격에 SELL 2개가 대기중이면 BUY 1개는 먼저 온 것과만 체결된다`() {
        // given: 책에 SELL 0.5 @ 500만 대기
        val orderBook = OrderBook()
        var sell = Order(
            id = 1L,
            side = Side.SELL,
            type = OrderType.LIMIT,
            price = BigDecimal.valueOf(5_000_000L),
            quantity = BigDecimal.valueOf(1L),
            remaining = BigDecimal.valueOf(1L)
        )
        orderBook.submit(sell)

        sell = Order(
            id = 2L,
            side = Side.SELL,
            type = OrderType.LIMIT,
            price = BigDecimal.valueOf(5_000_000L),
            quantity = BigDecimal.valueOf(1L),
            remaining = BigDecimal.valueOf(1L)
        )
        orderBook.submit(sell)

        // when: BUY 0.5 @ 500만 도착
        val buy = Order(
            id = 3L,
            side = Side.BUY,
            type = OrderType.LIMIT,
            price = BigDecimal.valueOf(5_000_000L),
            quantity = BigDecimal.valueOf(1L),
            remaining = BigDecimal.valueOf(1L)
        )
        val actual = orderBook.submit(buy)

        // then: Trade 1건(0.5 @ 500만), 둘 다 완전 체결, 책은 빔
        assertTrue(actual is MatchResult.Success)
        val success = actual as MatchResult.Success
        assertEquals(1, success.trades.size)
        assertEquals(0, success.trades[0].price.compareTo(BigDecimal.valueOf(5_000_000L)))
        assertEquals(1L, success.trades[0].makerOrderId)
        assertNull(success.remainingOrder)
        assertNotNull(orderBook.bestAsk())
    }
}
