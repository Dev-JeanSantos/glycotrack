package com.glycotrack.adapter.persistence.mapper


import com.glycotrack.domain.enum.MeasurementType
import com.glycotrack.domain.model.*
import com.glycotrack.adapter.persistence.entity.*
import org.springframework.stereotype.Component

@Component
class GlucoseMeasurementMapper {

    fun toEntity(domain: GlucoseMeasurement): GlucoseMeasurementEntity =
        GlucoseMeasurementEntity(
            id = domain.id,
            patientId = domain.patientId,
            timestamp = domain.timestamp,
            valueMgPerDl = domain.valueMgPerDl,
            type = domain.type.name,
            minutesAfterMeal = domain.minutesAfterMeal,
            meal = domain.meal?.let { meal ->
                MealEntity(
                    id = null,
                    description = meal.description,
                    totalCalories = meal.totalCalories,
                    items = meal.items.map { item ->
                        FoodItemEntity(
                            id = null,
                            name = item.name,
                            quantity = item.quantity,
                            unit = item.unit,
                            carbohydratesGrams = item.carbohydratesGrams,
                            proteinsGrams = item.proteinsGrams,
                            fatsGrams = item.fatsGrams,
                            calories = item.calories,
                            meal = null
                        )
                    }.toMutableList()
                )
            }
        )

    fun toDomain(entity: GlucoseMeasurementEntity): GlucoseMeasurement =
        GlucoseMeasurement(
            id = entity.id,
            patientId = entity.patientId,
            timestamp = entity.timestamp,
            valueMgPerDl = entity.valueMgPerDl,
            type = try { MeasurementType.valueOf(entity.type) } catch (e: Exception) { MeasurementType.RANDOM },
            minutesAfterMeal = entity.minutesAfterMeal,
            meal = entity.meal?.let { mealEntity ->
                Meal(
                    description = mealEntity.description,
                    totalCalories = mealEntity.totalCalories,
                    items = mealEntity.items.map { it.toDomain() }
                )
            }
        )

    private fun FoodItemEntity.toDomain() = FoodItem(
        name = this.name,
        quantity = this.quantity,
        unit = this.unit,
        carbohydratesGrams = this.carbohydratesGrams,
        proteinsGrams = this.proteinsGrams,
        fatsGrams = this.fatsGrams,
        calories = this.calories
    )
}
