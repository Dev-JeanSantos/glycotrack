package com.glycotrack.application.port.`in`

import com.glycotrack.domain.model.GlucoseMeasurement

interface SaveGlucoseMeasurementPort {
    fun save(measurement: GlucoseMeasurement): GlucoseMeasurement
}
