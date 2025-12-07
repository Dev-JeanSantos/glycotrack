package com.glycotrack.application.port.out

import com.glycotrack.domain.model.GlucoseMeasurement
import java.time.LocalDateTime

interface FindMeasurementsByPeriodPort {
    fun findAllByPatientId(patientId: Long, from: LocalDateTime, to: LocalDateTime): List<GlucoseMeasurement>
}