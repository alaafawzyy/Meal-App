package com.example.data.contract

import com.example.domain.model.Category


interface MealsOnlineDataSource {
    suspend fun getMeals():List<Category>
}