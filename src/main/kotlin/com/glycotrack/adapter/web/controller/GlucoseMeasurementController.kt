package com.glycotrack.adapter.web.controller

import com.glycotrack.application.port.`in`.RegisterGlucoseMeasurementPort
import com.glycotrack.application.port.out.FindMeasurementsByPatientIdPort
import com.glycotrack.application.port.out.FindMeasurementsByPeriodPort
import com.glycotrack.domain.model.GlucoseMeasurement
import com.glycotrack.adapter.web.dto.GlucoseMeasurementRequest
import com.glycotrack.adapter.web.mapper.WebMapper
import com.glycotrack.application.usecase.GetGlucoseMeasurementsUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1/api/measurements")
class GlucoseMeasurementController(
    private val registerPort: RegisterGlucoseMeasurementPort,
    private val findPort: FindMeasurementsByPeriodPort,
    private val getGlucoseMeasurementsUseCase: GetGlucoseMeasurementsUseCase,
    private val mapper: WebMapper
) {

    @PostMapping
    fun create(@RequestBody request: GlucoseMeasurementRequest) =
        ResponseEntity.ok(mapper.toResponse(registerPort.register(mapper.toDomain(request))))

    @GetMapping
    fun listByPeriod(
        @RequestParam patientId: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) from: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) to: LocalDateTime
    ) = ResponseEntity.ok(findPort.find(patientId, from, to).map { mapper.toResponse(it) })

    @GetMapping("/patient/{patientId}")
    fun getAllByPatientId(@PathVariable patientId: Long): List<GlucoseMeasurement> {
        return getGlucoseMeasurementsUseCase.execute(patientId)
    }
}
