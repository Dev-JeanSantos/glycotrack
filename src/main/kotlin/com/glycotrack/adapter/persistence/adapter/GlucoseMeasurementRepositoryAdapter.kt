package com.glycotrack.adapter.persistence.adapter


import com.glycotrack.adapter.persistence.mapper.GlucoseMeasurementMapper
import com.glycotrack.adapter.persistence.repository.GlucoseMeasurementJpaRepository
import com.glycotrack.application.port.out.FindMeasurementsByPeriodPort
import com.glycotrack.application.port.out.GetGlucoseMeasurementsPort
import com.glycotrack.application.port.out.SaveGlucoseMeasurementPort
import com.glycotrack.domain.model.GlucoseMeasurement
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GlucoseMeasurementRepositoryAdapter(
    private val jpaRepository: GlucoseMeasurementJpaRepository,
    private val mapper: GlucoseMeasurementMapper
) : SaveGlucoseMeasurementPort, FindMeasurementsByPeriodPort, GetGlucoseMeasurementsPort {

    override fun save(measurement: GlucoseMeasurement): GlucoseMeasurement {
        val entity = mapper.toEntity(measurement)
        val saved = jpaRepository.save(entity)
        return mapper.toDomain(saved)
    }

    override fun find(patientId: Long, from: LocalDateTime, to: LocalDateTime): List<GlucoseMeasurement> =
        jpaRepository.findByPatientIdAndTimestampBetween(patientId, from, to).map { mapper.toDomain(it) }

    override fun findAllByPatientId(patientId: Long): List<GlucoseMeasurement> {
        return jpaRepository.findAllByPatientId(patientId).map { mapper.toDomain(it) }
    }
}
