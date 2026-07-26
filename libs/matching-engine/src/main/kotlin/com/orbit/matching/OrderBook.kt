package com.orbit.matching

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.*

class OrderBook {

    private val bids = OrderBookSide(Side.BUY)
    private val asks = OrderBookSide(Side.SELL)

    fun submit(order: Order): MatchResult {
        val trades = mutableListOf<Trade>()
        when (order.side) {
            Side.BUY -> {
                var remainingQty = order.remaining;
                var lastOrder: Order? = null
                while (remainingQty > BigDecimal.ZERO && asks.bestPrice() != null && asks.bestPrice()!! <= order.price) {
                    val maker = asks.bestOrder() ?: break;
                    val makeQty = remainingQty.min(maker.remaining)
                    val madeOrder = maker.copy(remaining = maker.remaining - makeQty)
                    lastOrder = madeOrder
                    asks.put(madeOrder)
                    trades += Trade(
                        id = UUID.randomUUID(),
                        takerOrderId = order.id,   // 방금 들어온 주문 = taker
                        makerOrderId = maker.id,   // 책에서 기다리던 주문 = maker
                        price = maker.price,       // 체결가 = maker 가격 (가격 개선)
                        quantity = makeQty,
                        timestamp = ZonedDateTime.now()
                    )
                    remainingQty -= makeQty
                }
                if (remainingQty > BigDecimal.ZERO) {
                    bids.put(order)
                    return MatchResult.Success(trades, lastOrder)
                }
            }

            Side.SELL -> asks.put(order)
        }
        return MatchResult.Success(trades, null)
    }

    fun bestBid(): BigDecimal? {
        return bids.bestPrice()
    }

    fun bestAsk(): BigDecimal? {
        return asks.bestPrice()
    }
}
