package com.glycotrack.adapter.web.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.glycotrack.domain.enum.MeasurementType
import java.time.LocalDateTime

data class GlucoseMeasurementRequest(

    @JsonProperty("patient_id")
    val patientId: Long,

    @JsonProperty("timestamp")
    val timestamp: LocalDateTime,

    @JsonProperty("value_mg_per_dl")
    val valueMgPerDl: Int,

    @JsonProperty("type")
    val type: MeasurementType,

    @JsonProperty("minutes_after_meal")
    val minutesAfterMeal: Int? = null,

    @JsonProperty("meal")
    val meal: MealRequest? = null
)

data class MealRequest(

    @JsonProperty("description")
    val description: String? = null,

    @JsonProperty("items")
    val items: List<FoodItemRequest> = emptyList(),

    @JsonProperty("total_calories")
    val totalCalories: Int? = null
)

data class FoodItemRequest(

    @JsonProperty("name")
    val name: String,

    @JsonProperty("quantity")
    val quantity: Double? = null,

    @JsonProperty("unit")
    val unit: String? = null,

    @JsonProperty("carbohydrates_grams")
    val carbohydratesGrams: Double? = null,

    @JsonProperty("proteins_grams")
    val proteinsGrams: Double? = null,

    @JsonProperty("fats_grams")
    val fatsGrams: Double? = null,

    @JsonProperty("calories")
    val calories: Double? = null
)
