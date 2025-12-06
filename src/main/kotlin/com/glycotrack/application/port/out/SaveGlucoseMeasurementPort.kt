package com.glycotrack.application.port.out

import com.glycotrack.domain.model.GlucoseMeasurement

interface SaveGlucoseMeasurementPort {
    fun save(measurement: GlucoseMeasurement): GlucoseMeasurement
}
