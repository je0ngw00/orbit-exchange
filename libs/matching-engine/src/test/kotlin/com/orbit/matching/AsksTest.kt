package com.orbit.matching

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AsksTest {

    @Test
    fun `최저의 매칭가격이 나온다`() {
        //given
        val orderBookSide = OrderBookSide(Side.SELL)
        for(i in 1..10) {
            val order = Order(
                id = i.toLong(),
                side = Side.BUY,
                type = OrderType.LIMIT,
                price = BigDecimal.valueOf(1000L).multiply(BigDecimal.valueOf(i.toLong())),
                quantity = BigDecimal.valueOf(100L),
                remaining = BigDecimal.valueOf(100L)
            )
            orderBookSide.put(order)
        }

        //when
        val actual = orderBookSide.bestPrice()

        //then
        val expected = BigDecimal.valueOf(1000L)
        assertEquals(expected, actual)
    }

}
