package com.glycotrack.application.usecase

import com.glycotrack.domain.model.GlucoseMeasurement
import java.time.LocalDateTime

interface FindMeasurementsByPeriodUseCase {
    fun execute(patientId: Long, from: LocalDateTime, to: LocalDateTime): List<GlucoseMeasurement>
}
