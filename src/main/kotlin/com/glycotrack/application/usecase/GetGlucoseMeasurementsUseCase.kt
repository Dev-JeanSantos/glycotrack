package com.glycotrack.application.usecase

import com.glycotrack.domain.model.GlucoseMeasurement

interface GetGlucoseMeasurementsUseCase {
    fun execute(patientId: Long): List<GlucoseMeasurement>
}