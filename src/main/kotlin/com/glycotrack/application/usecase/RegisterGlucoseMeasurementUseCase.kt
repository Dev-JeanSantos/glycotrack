package com.glycotrack.application.usecase

import com.glycotrack.application.port.`in`.RegisterGlucoseMeasurementPort
import com.glycotrack.application.port.out.SaveGlucoseMeasurementPort
import com.glycotrack.domain.model.GlucoseMeasurement
import org.springframework.stereotype.Service

@Service
class RegisterGlucoseMeasurementUseCase(
    private val savePort: SaveGlucoseMeasurementPort
) : RegisterGlucoseMeasurementPort {

    override fun register(measurement: GlucoseMeasurement): GlucoseMeasurement {
        // business rules could be applied here (validation, enrichment, etc.)
        return savePort.save(measurement)
    }
}
