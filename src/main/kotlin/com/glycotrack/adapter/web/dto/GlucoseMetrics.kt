package com.glycotrack.adapter.web.dto

data class GlucoseMetrics(
    val patientId: Long,
    val meanFasting: Double?,
    val minFasting: Int?,
    val maxFasting: Int?,
    val meanPostMeal: Double?,
    val maxPostMeal: Int?,
    val avgMinutesAfterMeal: Double?,
    val meanRandom: Double?,
    val minRandom: Int?,
    val maxRandom: Int?,
    val dailyMetrics: List<DailyMetrics> = emptyList(),
    val cvOverall: Double?,
    val readingsOutOfRange: Int
)

data class DailyMetrics(
    val date: String,
    val mean: Double,
    val min: Int,
    val max: Int,
    val range: Int,
    val readingsOutOfRange: Int
)
