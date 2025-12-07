package com.glycotrack.application.usecase

import com.glycotrack.domain.model.GlucoseMeasurement

interface GetGlucoseMeasurementsByPatientIdUseCase {
    fun execute(patientId: Long): List<GlucoseMeasurement>
}