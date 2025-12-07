package com.glycotrack.application.usecase

import com.glycotrack.domain.model.GlucoseMeasurement

interface RegisterGlucoseMeasurementUseCase {
    fun execute(measurement: GlucoseMeasurement): GlucoseMeasurement
}
