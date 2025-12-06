package com.glycotrack.adapter.web.mapper

import com.glycotrack.domain.model.FoodItem
import com.glycotrack.domain.model.GlucoseMeasurement
import com.glycotrack.domain.model.Meal
import com.glycotrack.adapter.web.dto.*
import org.springframework.stereotype.Component

@Component
class WebMapper {

    fun toDomain(req: GlucoseMeasurementRequest) = GlucoseMeasurement(
        id = null,
        patientId = req.patientId,
        timestamp = req.timestamp,
        valueMgPerDl = req.valueMgPerDl,
        type = req.type,
        minutesAfterMeal = req.minutesAfterMeal,
        meal = req.meal?.let { mealReq ->
            Meal(
                description = mealReq.description,
                totalCalories = mealReq.totalCalories,
                items = mealReq.items.map { it.toDomain() }
            )
        }
    )

    fun toResponse(domain: GlucoseMeasurement) = GlucoseMeasurementResponse(
        id = domain.id,
        patientId = domain.patientId,
        timestamp = domain.timestamp,
        valueMgPerDl = domain.valueMgPerDl,
        type = domain.type,
        minutesAfterMeal = domain.minutesAfterMeal,
        meal = domain.meal?.let { meal ->
            MealResponse(
                description = meal.description,
                totalCalories = meal.totalCalories,
                items = meal.items.map { it.toResponse() }
            )
        }
    )

    private fun FoodItemRequest.toDomain() = FoodItem(
        name = this.name,
        quantity = this.quantity,
        unit = this.unit,
        carbohydratesGrams = this.carbohydratesGrams,
        proteinsGrams = this.proteinsGrams,
        fatsGrams = this.fatsGrams,
        calories = this.calories
    )

    private fun FoodItem.toResponse() = FoodItemResponse(
        name = this.name,
        quantity = this.quantity,
        unit = this.unit,
        carbohydratesGrams = this.carbohydratesGrams,
        proteinsGrams = this.proteinsGrams,
        fatsGrams = this.fatsGrams,
        calories = this.calories
    )
}
