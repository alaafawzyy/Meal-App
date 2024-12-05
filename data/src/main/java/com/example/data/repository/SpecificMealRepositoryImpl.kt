package com.example.data.repository

import com.example.data.contract.SpecificMealDataSource
import com.example.domain.model.Category
import com.example.domain.model.Meal
import com.example.domain.repo.SpecificCategoryRepository
import javax.inject.Inject

class SpecificMealRepositoryImpl @Inject constructor(private val specificMealDataSource: SpecificMealDataSource):SpecificCategoryRepository {


    override suspend fun getSpecificCategory(mealId: String): Meal {
        return specificMealDataSource.getSpecificMeaal(mealId)
    }
}