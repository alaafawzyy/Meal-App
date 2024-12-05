package com.example.domain.repo

import com.example.domain.model.Category


interface MealsRepository {
    suspend fun getMeals(): List<Category>
}