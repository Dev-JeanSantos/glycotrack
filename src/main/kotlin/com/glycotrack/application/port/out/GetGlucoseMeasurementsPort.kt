package com.glycotrack.application.port.out

import com.glycotrack.domain.model.GlucoseMeasurement

interface GetGlucoseMeasurementsPort {
    fun findAllByPatientId(patientId: Long): List<GlucoseMeasurement>
}