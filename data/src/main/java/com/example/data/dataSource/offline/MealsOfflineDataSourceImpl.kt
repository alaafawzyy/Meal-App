 package com.example.data.dataSource.offline

import com.example.data.contract.MealsOfflineDataSource
import com.example.data.database.MealsDataBase
import com.example.domain.model.Category
import jakarta.inject.Inject

class MealsOfflineDataSourceImpl @Inject constructor(private val mealsDataBase: MealsDataBase): MealsOfflineDataSource {
    override suspend fun getMeals(): List<Category> {

        return try {
            mealsDataBase.getCategoryDao().getCategory()
        }
        catch (e:Exception){
            throw e
        }
    }

    override suspend fun saveMeals(category: List<Category>) {
        try {
            mealsDataBase.getCategoryDao().saveCategory(category)
        }
        catch (e:Exception){
            throw e
        }
    }

}

