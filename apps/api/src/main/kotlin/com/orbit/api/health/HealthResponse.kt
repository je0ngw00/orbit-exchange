package com.orbit.api.health

data class HealthResponse(val status: String = "OK", val ts: Long = System.currentTimeMillis())
