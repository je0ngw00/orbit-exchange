package com.orbit.matching

import java.math.BigDecimal

data class Order(
    val id: Long,
    val side: Side,
    val type: OrderType,
    val price: BigDecimal,
    val quantity: BigDecimal,
    val remaining: BigDecimal
)
