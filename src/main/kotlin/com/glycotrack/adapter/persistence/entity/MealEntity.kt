package com.glycotrack.adapter.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "meals")
data class MealEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val description: String? = null,
    val totalCalories: Int? = null,
    @OneToMany(mappedBy = "meal", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: MutableList<FoodItemEntity> = mutableListOf()
)

