package com.glycotrack.application.port.`in`

import com.glycotrack.domain.model.GlucoseMeasurement

interface RegisterGlucoseMeasurementPort {
    fun register(measurement: GlucoseMeasurement): GlucoseMeasurement
}
