package com.example.data.api


import com.example.data.model.CategoryDto

import retrofit2.http.Query
import com.example.data.model.MealsResponse
import com.example.data.model.NewMealRespone
import retrofit2.http.GET

interface MealsServices {

    @GET("categories.php")
    suspend fun getMeals():MealsResponse


    @GET("search.php")
    suspend fun getMealsByCategory(@Query("s") categoryName: String): NewMealRespone





}