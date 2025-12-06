package com.glycotrack.domain.model

data class Meal(
    val description: String? = null,
    val items: List<FoodItem> = emptyList(),
    val totalCalories: Int? = null
)
