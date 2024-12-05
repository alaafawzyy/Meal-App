package com.example.data.contract

import com.example.domain.model.Category

interface MealsOfflineDataSource {
    suspend fun getMeals():List<Category>
    suspend fun saveMeals(category:List<Category>)
}