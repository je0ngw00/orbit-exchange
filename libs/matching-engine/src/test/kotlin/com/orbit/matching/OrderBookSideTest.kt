package com.orbit.matching

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class OrderBookSideTest {

    @Test
    fun `빈 호가창이면 bestBid는 null`() {
        val orderBookSide = OrderBookSide(Side.BUY)
        assertNull(orderBookSide.bestPrice())
    }

}
