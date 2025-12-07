package com.glycotrack.application.service

import com.glycotrack.application.port.out.FindMeasurementsByPeriodPort
import com.glycotrack.application.usecase.FindMeasurementsByPeriodUseCase
import com.glycotrack.domain.model.GlucoseMeasurement
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class FindMeasurementsByPeriodService(
    private val port: FindMeasurementsByPeriodPort
) : FindMeasurementsByPeriodUseCase {
    override fun execute(patientId: Long, from: LocalDate?, to: LocalDate?): List<GlucoseMeasurement> {
       val newFrom = from?.atTime(LocalTime.MAX)
       val newTo = to?.atTime(LocalTime.MAX)
       return port.findAllByPatientId(patientId, newFrom, newTo)
    }



}