package com.example.domain.repo

import com.example.domain.model.Meal

interface SpecificCategoryRepository {
    suspend fun getSpecificCategory(mealId: String):Meal
}