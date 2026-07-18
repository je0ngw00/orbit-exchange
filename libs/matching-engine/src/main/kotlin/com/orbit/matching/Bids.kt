package com.orbit.matching

import java.math.BigDecimal
import java.util.TreeMap

class Bids {
    private val orderQueue = TreeMap<BigDecimal, ArrayDeque<Order>>(reverseOrder())

    fun put(order: Order): Bids {
        val orders = orderQueue.getOrPut(order.price) { ArrayDeque<Order>() }
        orders.addLast(order)
        return this
    }

    fun bestBid(): BigDecimal? {
        return orderQueue.firstEntry()?.key
    }
}
