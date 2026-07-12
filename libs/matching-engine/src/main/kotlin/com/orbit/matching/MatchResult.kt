package com.orbit.matching

sealed class MatchResult {
    data class Success(val trades: List<Trade>, val remainingOrder: Order?) : MatchResult()
    data class Reject(val rejected: Order, val reason: String) : MatchResult()
}
