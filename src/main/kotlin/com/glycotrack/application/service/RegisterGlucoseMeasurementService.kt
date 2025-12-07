package com.glycotrack.application.service

import com.glycotrack.application.port.out.SaveGlucoseMeasurementPort
import com.glycotrack.application.usecase.RegisterGlucoseMeasurementUseCase
import com.glycotrack.domain.model.GlucoseMeasurement
import org.springframework.stereotype.Service

@Service
class RegisterGlucoseMeasurementService(
    private val savePort: SaveGlucoseMeasurementPort
) : RegisterGlucoseMeasurementUseCase {

    override fun execute(measurement: GlucoseMeasurement): GlucoseMeasurement {
        return savePort.save(measurement)
    }
}