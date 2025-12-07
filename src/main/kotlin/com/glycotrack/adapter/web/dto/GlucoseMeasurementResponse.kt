package com.glycotrack.adapter.web.dto

import com.glycotrack.domain.enum.MeasurementType
import java.time.LocalDateTime

data class GlucoseMeasurementResponse(
    val id: Long? = null,
    val patientId: Long,
    val timestamp: LocalDateTime?,
    val valueMgPerDl: Int,
    val type: MeasurementType,
    val minutesAfterMeal: Int? = null,
    val meal: MealResponse? = null
)

data class MealResponse(
    val description: String? = null,
    val items: List<FoodItemResponse> = emptyList(),
    val totalCalories: Int? = null
)

data class FoodItemResponse(
    val name: String,
    val quantity: Double? = null,
    val unit: String? = null,
    val carbohydratesGrams: Double? = null,
    val proteinsGrams: Double? = null,
    val fatsGrams: Double? = null,
    val calories: Double? = null
)
