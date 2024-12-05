package com.example.mealsapplication

import android.app.Application
import com.example.data.database.MealsDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MealsApp:Application() {
    override fun onCreate() {
        super.onCreate()
        MealsDataBase.init(this)
    }
}