package com.glycotrack.infrastructure.metrics

import com.glycotrack.application.service.GlucoseMetricsService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MetricsUpdater(private val glucoseMetricsService: GlucoseMetricsService) {
    private val logger = LoggerFactory.getLogger(MetricsUpdater::class.java)

    @Scheduled(fixedRate = 10000)
    fun updateAll() {
        logger.info("execution metrics")
        glucoseMetricsService.calculateMetrics(1L)
    }
}
