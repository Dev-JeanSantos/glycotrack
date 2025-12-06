package com.glycotrack.adapter.persistence.entity


import jakarta.persistence.*

@Entity
@Table(name = "food_items")
data class FoodItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val quantity: Double? = null,
    val unit: String? = null,
    val carbohydratesGrams: Double? = null,
    val proteinsGrams: Double? = null,
    val fatsGrams: Double? = null,
    val calories: Double? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    var meal: MealEntity? = null
)
