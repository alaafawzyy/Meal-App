package com.example.data.dataSource

import android.util.Log
import com.example.data.api.MealsServices
import com.example.data.contract.SpecificMealDataSource
import com.example.data.model.NewMealRespone
import com.example.domain.model.Category
import com.example.domain.model.Meal
import javax.inject.Inject

class SpecificMealDataSourceImpl @Inject constructor(private val api:MealsServices) :SpecificMealDataSource{
    override suspend fun getSpecificMeaal(id: String): Meal {
val meal=api.getMealsByCategory(id)
       // Log.w("alaa","viewmodelzzz ${meal}")
        return try {
            val response = api.getMealsByCategory(id)
            response.meals?.map { it?.toMeal() }?.firstOrNull()
                ?: Meal("","","","","","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","",
                    "","","","","")
        }
        catch (e:Exception){
            throw e
        }
    }
}