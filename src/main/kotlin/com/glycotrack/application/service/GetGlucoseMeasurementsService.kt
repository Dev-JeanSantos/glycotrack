package com.glycotrack.application.service

import com.glycotrack.application.port.out.GetGlucoseMeasurementsPort
import com.glycotrack.application.usecase.GetGlucoseMeasurementsUseCase
import org.springframework.stereotype.Service

@Service
class GetGlucoseMeasurementsService(
    private val port: GetGlucoseMeasurementsPort
) : GetGlucoseMeasurementsUseCase {

    override fun execute(patientId: Long) =
        port.findAllByPatientId(patientId)
}