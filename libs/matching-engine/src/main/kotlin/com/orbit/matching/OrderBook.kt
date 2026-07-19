package com.orbit.matching

import java.math.BigDecimal

class OrderBook {

    private val bids = OrderBookSide(Side.BUY)
    private val asks = OrderBookSide(Side.SELL)

    fun submit(order: Order): MatchResult {
        when (order.side) {
            Side.BUY -> bids.put(order)
            Side.SELL -> asks.put(order)
        }
        return MatchResult.Success(emptyList(), order)
    }
    fun bestBid(): BigDecimal? {
        return bids.bestPrice()
    }
    fun bestAsk(): BigDecimal? {
        return asks.bestPrice()
    }
}
