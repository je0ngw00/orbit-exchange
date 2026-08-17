package com.orbit.matching

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID


class OrderBook {

    private data class Fill(val trades: List<Trade>, val remainingQty: BigDecimal) {
        fun hasRemainingQty(): Boolean {
            return remainingQty > BigDecimal.ZERO
        }
    }

    private val bids = OrderBookSide(Side.BUY)
    private val asks = OrderBookSide(Side.SELL)

    fun submit(order: Order): MatchResult {
        when (order.side) {
            Side.BUY -> {
                return buying(order)
            }
            Side.SELL -> asks.put(order)
        }
        return MatchResult.Success(mutableListOf<Trade>(), null)
    }

    private fun buying(order: Order): MatchResult.Success {
        val fill = match(order)
        if (fill.hasRemainingQty()) {
            bids.put(order)
            return MatchResult.Success(fill.trades, order.copy(remaining = fill.remainingQty))
        }
        return MatchResult.Success(fill.trades, null);
    }

    private fun match(taker: Order): Fill {
        val trades = mutableListOf<Trade>()
        var remainingQty = taker.remaining
        while (remainingQty > BigDecimal.ZERO) {
            val bestPrice = asks.bestPrice() ?: break
            if (bestPrice > taker.price) break

            val maker = asks.bestOrder() ?: break
            val makeQty = remainingQty.min(maker.remaining)
            asks.removeFirst()
            trades += Trade(
                id = UUID.randomUUID(),
                takerOrderId = taker.id,   // 방금 들어온 주문 = taker
                makerOrderId = maker.id,   // 책에서 기다리던 주문 = maker
                price = maker.price,       // 체결가 = maker 가격 (가격 개선)
                quantity = makeQty,
                timestamp = ZonedDateTime.now()
            )
            remainingQty -= makeQty
        }
        return Fill(trades, remainingQty)
    }

    fun bestBid(): BigDecimal? {
        return bids.bestPrice()
    }

    fun bestAsk(): BigDecimal? {
        return asks.bestPrice()
    }
}
