package com.glycotrack.adapter.persistence.repository


import com.glycotrack.adapter.persistence.entity.GlucoseMeasurementEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
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

    @Query("""
    SELECT m FROM GlucoseMeasurementEntity m
    LEFT JOIN FETCH m.meal meal
    LEFT JOIN FETCH meal.items
    WHERE m.patientId = :patientId
""")
    fun findAllByPatientIdEager(@Param("patientId") patientId: Long): List<GlucoseMeasurementEntity>
}
