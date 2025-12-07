package com.glycotrack.adapter.web.controller

import com.glycotrack.adapter.web.dto.GlucoseMeasurementRequest
import com.glycotrack.adapter.web.dto.GlucoseMeasurementResponse
import com.glycotrack.adapter.web.mapper.WebMapper
import com.glycotrack.application.usecase.FindMeasurementsByPeriodUseCase
import com.glycotrack.application.usecase.GetGlucoseMeasurementsByPatientIdUseCase
import com.glycotrack.application.usecase.RegisterGlucoseMeasurementUseCase
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/v1/api/measurements")
class GlucoseMeasurementController(
    private val registerGlucoseMeasurementUseCase: RegisterGlucoseMeasurementUseCase,
    private val findMeasurementsByPeriodUseCase: FindMeasurementsByPeriodUseCase,
    private val getGlucoseMeasurementsByPatientIdUseCase: GetGlucoseMeasurementsByPatientIdUseCase,
    private val mapper: WebMapper
) {

    @PostMapping
    fun create(@RequestBody request: GlucoseMeasurementRequest): ResponseEntity<GlucoseMeasurementResponse> {
        val domain = mapper.toDomain(request)
        val saved = registerGlucoseMeasurementUseCase.execute(domain)
        val response = mapper.toResponse(saved)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response)
    }


    @GetMapping
    fun listByPeriod(

        @RequestParam("patient_id") patientId: Long,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        from: LocalDate? = null,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        to: LocalDate? = null
    ) = ResponseEntity.ok(findMeasurementsByPeriodUseCase.execute(patientId, from, to).map { mapper.toResponse(it) })

    @GetMapping("/patient/{patientId}")
    fun getAllByPatientId(@PathVariable patientId: Long): ResponseEntity<List<GlucoseMeasurementResponse>> {
        val measurements = getGlucoseMeasurementsByPatientIdUseCase.execute(patientId)
        return ResponseEntity.ok(measurements.map { mapper.toResponse(it) })
    }
}
