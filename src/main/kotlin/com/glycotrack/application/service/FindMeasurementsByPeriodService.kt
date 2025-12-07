package com.glycotrack.application.service

import com.glycotrack.application.port.out.FindMeasurementsByPeriodPort
import com.glycotrack.application.usecase.FindMeasurementsByPeriodUseCase
import com.glycotrack.domain.model.GlucoseMeasurement
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FindMeasurementsByPeriodService(
    private val port: FindMeasurementsByPeriodPort
) : FindMeasurementsByPeriodUseCase {
    override fun execute(patientId: Long, from: LocalDateTime, to: LocalDateTime): List<GlucoseMeasurement> {
       return port.findAllByPatientId(patientId, from, to)
    }

}