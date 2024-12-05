package com.example.data.model


import com.google.gson.annotations.SerializedName

data class NewMealRespone(
    @SerializedName("meals")
    val meals: List<MealDto?>?
)