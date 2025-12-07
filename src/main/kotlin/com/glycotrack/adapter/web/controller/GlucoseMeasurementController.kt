package com.glycotrack.adapter.web.controller

import com.glycotrack.adapter.web.dto.GlucoseMeasurementRequest
import com.glycotrack.adapter.web.dto.GlucoseMeasurementResponse
import com.glycotrack.adapter.web.mapper.WebMapper
import com.glycotrack.application.port.`in`.RegisterGlucoseMeasurementPort
import com.glycotrack.application.usecase.FindMeasurementsByPeriodUseCase
import com.glycotrack.application.usecase.GetGlucoseMeasurementsByPatientIdUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/v1/api/measurements")
class GlucoseMeasurementController(
    private val registerPort: RegisterGlucoseMeasurementPort,
    private val findMeasurementsByPeriodUseCase: FindMeasurementsByPeriodUseCase,
    private val getGlucoseMeasurementsByPatientIdUseCase: GetGlucoseMeasurementsByPatientIdUseCase,
    private val mapper: WebMapper
) {

    @PostMapping
    fun create(@RequestBody request: GlucoseMeasurementRequest): ResponseEntity<GlucoseMeasurementResponse> {
        val domain = mapper.toDomain(request)
        val saved = registerPort.register(domain)
        val response = mapper.toResponse(saved)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }


    @GetMapping
    fun listByPeriod(
        @RequestParam patientId: Long,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) from: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) to: LocalDateTime
    ) = ResponseEntity.ok(findMeasurementsByPeriodUseCase.execute(patientId, from, to).map { mapper.toResponse(it) })

    @GetMapping("/patient/{patientId}")
    fun getAllByPatientId(@PathVariable patientId: Long): ResponseEntity<List<GlucoseMeasurementResponse>> {
        val measurements = getGlucoseMeasurementsByPatientIdUseCase.execute(patientId)
        return ResponseEntity.ok(measurements.map { mapper.toResponse(it) })
    }
}
