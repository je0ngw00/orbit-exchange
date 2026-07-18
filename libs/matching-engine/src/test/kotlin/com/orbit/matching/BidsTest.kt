package com.orbit.matching

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BidsTest {

    @Test
    fun `최고의 매칭가격이 나온다`() {
        //given
        val bids = Bids()
        for(i in 1..10) {
            val order = Order(
                id = i.toLong(),
                side = Side.BUY,
                type = OrderType.LIMIT,
                price = BigDecimal.valueOf(1000L).multiply(BigDecimal.valueOf(i.toLong())),
                quantity = BigDecimal.valueOf(100L),
                remaining = BigDecimal.valueOf(100L)
            )
            bids.put(order)
        }

        //when
        val actual = bids.bestBid()

        //then
        val expected = BigDecimal.valueOf(10000L)
        assertEquals(expected, actual)
    }

    @Test
    fun `빈 호가창이면 bestBid는 null`() {
        val bids = Bids()
        assertNull(bids.bestBid())
    }

}
