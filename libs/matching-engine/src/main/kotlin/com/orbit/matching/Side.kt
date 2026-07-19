package com.orbit.matching

import java.math.BigDecimal

enum class Side(val label: String, val priceComparator: Comparator<BigDecimal>) {
    BUY ("매수", reverseOrder()),
    SELL ("매도", naturalOrder())
}
