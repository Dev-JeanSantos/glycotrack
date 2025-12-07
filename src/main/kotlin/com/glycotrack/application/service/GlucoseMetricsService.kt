package com.glycotrack.application.service

import com.glycotrack.adapter.web.dto.GlucoseMetrics
import com.glycotrack.application.port.out.FindAllByPatientIdEagerPort
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference

@Service
class GlucoseMetricsService(
    private val findAllByPatientIdEagerPort: FindAllByPatientIdEagerPort,
    private val meterRegistry: MeterRegistry
) {

    private val overallMax = ConcurrentHashMap<Long, AtomicReference<Double>>()
    private val overallMin = ConcurrentHashMap<Long, AtomicReference<Double>>()

    private fun ensureGaugeRegistered(name: String, patientId: Long, ref: AtomicReference<Double>) {
        // Registrar apenas se ainda não tiver registrado
        val existingGauge = meterRegistry.find(name).tags("patientId", patientId.toString()).gauge()
        if (existingGauge == null) { // <-- aqui
            Gauge.builder(name, ref) { it.get() }
                .tags("patientId", patientId.toString())
                .register(meterRegistry)
        }
    }

    fun calculateMetrics(patientId: Long): GlucoseMetrics {
        val measurements = findAllByPatientIdEagerPort.findAllByPatientIdEager(patientId)
        val allValues = measurements.mapNotNull { it.valueMgPerDl }

        overallMax.putIfAbsent(patientId, AtomicReference(0.0))
        overallMin.putIfAbsent(patientId, AtomicReference(0.0))

        if (allValues.isNotEmpty()) {
            overallMax[patientId]?.set(allValues.maxOrNull()?.toDouble() ?: 0.0)
            overallMin[patientId]?.set(allValues.minOrNull()?.toDouble() ?: 0.0)
        }

        // Registra gauges apenas uma vez
        ensureGaugeRegistered("glucose_overall_max", patientId, overallMax[patientId]!!)
        ensureGaugeRegistered("glucose_overall_min", patientId, overallMin[patientId]!!)

        return GlucoseMetrics(
            patientId = patientId,
            meanFasting = 0.0,
            minFasting = overallMin[patientId]?.get()?.toInt() ?: 0,
            maxFasting = overallMax[patientId]?.get()?.toInt() ?: 0,
            meanPostMeal = 0.0,
            maxPostMeal = 0,
            avgMinutesAfterMeal = null,
            meanRandom = null,
            minRandom = null,
            maxRandom = null,
            dailyMetrics = emptyList(),
            cvOverall = null,
            readingsOutOfRange = 0
        )
    }
}
