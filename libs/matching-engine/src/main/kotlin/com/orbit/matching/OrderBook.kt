package com.orbit.matching

import java.math.BigDecimal
import java.util.TreeMap

class OrderBook() {

    private val bids = Bids()
    private val asks = TreeMap<BigDecimal, ArrayDeque<Order>>()

    fun submit(order: Order): MatchResult {
        bids.put(order);
        return MatchResult.Success(emptyList(), order)
    }
    fun bestBid(): BigDecimal? {
        return bids.bestBid()
    }
    fun bestAsk(): BigDecimal? = TODO()
}
