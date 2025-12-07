package com.glycotrack.application.service

import com.glycotrack.adapter.web.dto.GlucoseMetrics
import com.glycotrack.application.port.out.FindMeasurementsByPatientIdPort
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicReference

@Service
class GlucoseMetricsService(
    private val findMeasurementsByPatientIdPort: FindMeasurementsByPatientIdPort,
    private val meterRegistry: MeterRegistry
) {

    private val fastingAvg = AtomicReference(0.0)
    private val postMealAvg = AtomicReference(0.0)
    private val overallMax = AtomicReference(0.0)
    private val overallMin = AtomicReference(0.0)

    init {
        Gauge.builder("glucose.fasting.avg") { fastingAvg.get() }
            .register(meterRegistry)
        Gauge.builder("glucose.post_meal.avg") { postMealAvg.get() }
            .register(meterRegistry)
        Gauge.builder("glucose.overall.max") { overallMax.get() }
            .register(meterRegistry)
        Gauge.builder("glucose.overall.min") { overallMin.get() }
            .register(meterRegistry)
    }

    fun calculateMetrics(patientId: Long): GlucoseMetrics {
        val measurements = findMeasurementsByPatientIdPort.findAllByPatientId(patientId)

        val allValues = measurements.mapNotNull { it.valueMgPerDl }
        val fasting = measurements.filter { it.type.name == "FASTING" }.mapNotNull { it.valueMgPerDl }
        val postMeal = measurements.filter { it.type.name == "POST_MEAL" }.mapNotNull { it.valueMgPerDl }

        if (allValues.isNotEmpty()) {
            fastingAvg.set(fasting.averageOrNull() ?: 0.0)
            postMealAvg.set(postMeal.averageOrNull() ?: 0.0)
            overallMax.set(allValues.maxOrNull()?.toDouble() ?: 0.0)
            overallMin.set(allValues.minOrNull()?.toDouble() ?: 0.0)
        }

        return GlucoseMetrics(
            patientId = patientId,
            meanFasting = fastingAvg.get(),
            minFasting = overallMin.get().toInt(),
            maxFasting = overallMax.get().toInt(),
            meanPostMeal = postMealAvg.get(),
            maxPostMeal = postMealAvg.get().toInt(),
            avgMinutesAfterMeal = null,
            meanRandom = null,
            minRandom = null,
            maxRandom = null,
            dailyMetrics = emptyList(),
            cvOverall = null,
            readingsOutOfRange = 0
        )
    }

    private fun List<Int>.averageOrNull(): Double? = if (isNotEmpty()) average() else null
}
