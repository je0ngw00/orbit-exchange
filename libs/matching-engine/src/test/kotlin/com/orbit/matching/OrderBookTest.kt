package com.orbit.matching

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
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

    @Test
    fun `BUY 주문을 넣으면 bestBid가 그 가격`() {
        //given
        val orderBook = OrderBook()
        val expected = BigDecimal.valueOf(1000L)
        val order = Order(
            id = 1L,
            side = Side.BUY,
            type = OrderType.LIMIT,
            price = expected,
            quantity = BigDecimal.valueOf(100L),
            remaining = BigDecimal.valueOf(100L)
        )

        //when
        orderBook.submit(order)
        val actual = orderBook.bestBid()

        //then
        assertEquals(expected, actual)
    }

    @Test
    fun `SELL 주문을 넣으면 bestAsk가 그 가격`() {
        //given
        val orderBook = OrderBook()
        val expected = BigDecimal.valueOf(500L)
        val order = Order(
            id = 1L,
            side = Side.SELL,
            type = OrderType.LIMIT,
            price = expected,
            quantity = BigDecimal.valueOf(100L),
            remaining = BigDecimal.valueOf(100L)
        )

        //when
        orderBook.submit(order)
        val actual = orderBook.bestAsk()

        //then
        assertEquals(expected, actual)
    }
}
