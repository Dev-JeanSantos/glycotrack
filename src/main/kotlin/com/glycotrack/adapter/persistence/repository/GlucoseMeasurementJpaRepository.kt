package com.glycotrack.adapter.persistence.repository


import com.glycotrack.adapter.persistence.entity.GlucoseMeasurementEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface GlucoseMeasurementJpaRepository : JpaRepository<GlucoseMeasurementEntity, Long> {

    fun findByPatientIdAndTimestampGreaterThanEqual(
        patientId: Long,
        from: LocalDateTime
    ): List<GlucoseMeasurementEntity>

    fun findByPatientIdAndTimestampBetween(
        patientId: Long,
        from: LocalDateTime,
        to: LocalDateTime
    ): List<GlucoseMeasurementEntity>

    fun findAllByPatientId(patientId: Long): List<GlucoseMeasurementEntity>
}
