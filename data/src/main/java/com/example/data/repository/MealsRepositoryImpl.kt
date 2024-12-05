package com.example.data.repository


import com.example.data.NetworkHandler
import com.example.data.contract.MealsOfflineDataSource
import com.example.data.contract.MealsOnlineDataSource
import com.example.domain.model.Category
import com.example.domain.repo.MealsRepository
import javax.inject.Inject

class MealsRepositoryImpl @Inject constructor(
    private val mealsOnlineDataSource: MealsOnlineDataSource,
    private val mealsOfflineDataSource: MealsOfflineDataSource,
    private val networkHandler: NetworkHandler
):MealsRepository {
    override suspend fun getMeals(): List<Category> {

        if(networkHandler.isOnline()){
            val meals=mealsOnlineDataSource.getMeals()
            mealsOfflineDataSource.saveMeals(meals)
            return meals
        }
        else{
      return mealsOfflineDataSource.getMeals() }
        }
    }

