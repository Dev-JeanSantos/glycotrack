package com.glycotrack.domain.model

data class FoodItem(
    val name: String,
    val quantity: Double? = null,
    val unit: String? = null,
    val carbohydratesGrams: Double? = null,
    val proteinsGrams: Double? = null,
    val fatsGrams: Double? = null,
    val calories: Double? = null
)
