package com.glycotrack.application.service

import com.glycotrack.application.port.out.FindMeasurementsByPatientIdPort
import com.glycotrack.application.usecase.GetGlucoseMeasurementsByPatientIdUseCase
import org.springframework.stereotype.Service

@Service
class GetGlucoseMeasurementsByPatientIdService(
    private val port: FindMeasurementsByPatientIdPort
) : GetGlucoseMeasurementsByPatientIdUseCase {

    override fun execute(patientId: Long) =
        port.findAllByPatientId(patientId)
}