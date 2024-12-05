package com.example.data.di

import com.example.data.database.MealsDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModulue {

    @Singleton
    @Provides
    fun provideNewsDataBase():MealsDataBase{
        return MealsDataBase.getInctance()
    }
}