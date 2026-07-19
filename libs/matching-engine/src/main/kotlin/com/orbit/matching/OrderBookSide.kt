package com.orbit.matching

import java.math.BigDecimal
import java.util.TreeMap
import kotlin.collections.ArrayDeque
import kotlin.collections.getOrPut

class OrderBookSide (val side: Side) {
    private val orderQueue = TreeMap<BigDecimal, ArrayDeque<Order>>(side.priceComparator)

    fun put(order: Order): OrderBookSide {
        val orders = orderQueue.getOrPut(order.price) { ArrayDeque() }
        orders.addLast(order)
        return this
    }

    fun bestPrice(): BigDecimal? {
        return orderQueue.firstEntry()?.key
    }
}
