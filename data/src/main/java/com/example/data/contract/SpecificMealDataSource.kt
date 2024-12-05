package com.example.data.contract


import com.example.data.model.NewMealRespone
import com.example.domain.model.Meal

interface SpecificMealDataSource {
    suspend fun getSpecificMeaal(id:String):Meal
}