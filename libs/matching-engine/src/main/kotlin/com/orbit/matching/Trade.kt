package com.orbit.matching

import java.math.BigDecimal
import java.time.ZonedDateTime

data class Trade(
    val id: Long,
    val takerOrderId: Long,
    val makerOrderId: Long,
    val price: BigDecimal,
    val quantity: BigDecimal,
    val timestamp: ZonedDateTime
)
