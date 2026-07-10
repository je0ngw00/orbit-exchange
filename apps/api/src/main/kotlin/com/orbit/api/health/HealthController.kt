package com.orbit.api.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1")
class HealthController {

    @GetMapping("/health")
    suspend fun health(): HealthResponse {
        return HealthResponse()
    }
}
