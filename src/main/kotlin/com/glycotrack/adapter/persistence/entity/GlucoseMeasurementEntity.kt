package com.glycotrack.adapter.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "glucose_measurements")
data class GlucoseMeasurementEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val patientId: Long,
    val timestamp: LocalDateTime,
    val valueMgPerDl: Int,
    val type: String,
    val minutesAfterMeal: Int? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "meal_id")
    val meal: MealEntity? = null
)
