package com.example.data.dataSource.online

import com.example.data.api.MealsServices
import com.example.data.contract.MealsOnlineDataSource
import com.example.domain.model.Category
import javax.inject.Inject

class MealsOnlineDataSourceImpl @Inject constructor(private val mealsServices: MealsServices):MealsOnlineDataSource {
    override suspend fun getMeals(): List<Category> {
        return try {
            mealsServices.getMeals().categories.map {
                it.toCategory()
            }
        }
        catch (e:Exception){
            throw e
        }
    }
}