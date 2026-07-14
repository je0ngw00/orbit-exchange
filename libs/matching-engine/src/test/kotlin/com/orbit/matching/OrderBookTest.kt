package com.orbit.matching

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal


class OrderBookTest {

    @Test
    fun `빈 호가창에 BUY LIMIT 넣으면 체결 없이 책에 대기`() {
        //given
        val orderBook = OrderBook()
        val order = Order(
            id = 1L,
            side = Side.BUY,
            type = OrderType.LIMIT,
            price = BigDecimal.valueOf(1000L),
            quantity = BigDecimal.valueOf(100L),
            remaining = BigDecimal.valueOf(100L)
        )

        //when
        val actual = orderBook.submit(order)

        //then
        val expected = MatchResult.Success(emptyList(), order)
        assertEquals(expected, actual)
    }
}
