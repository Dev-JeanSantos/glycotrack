package com.glycotrack.application.usecase

import com.glycotrack.domain.model.GlucoseMeasurement
import java.time.LocalDate

interface FindMeasurementsByPeriodUseCase {
    fun execute(patientId: Long, from: LocalDate?, to: LocalDate?): List<GlucoseMeasurement>
}
