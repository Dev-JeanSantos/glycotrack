package com.glycotrack.domain.model

import com.glycotrack.domain.enum.MeasurementType
import java.time.LocalDateTime

data class GlucoseMeasurement(
    val id: Long? = null,
    val patientId: Long,
    val timestamp: LocalDateTime,
    val valueMgPerDl: Int,
    val type: MeasurementType,
    val minutesAfterMeal: Int? = null,
    val meal: Meal? = null
)
