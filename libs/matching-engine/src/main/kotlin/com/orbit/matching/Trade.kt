package com.orbit.matching

import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class Trade(
    val id: UUID,
    val takerOrderId: Long,
    val makerOrderId: Long,
    val price: BigDecimal,
    val quantity: BigDecimal,
    val timestamp: ZonedDateTime
)
