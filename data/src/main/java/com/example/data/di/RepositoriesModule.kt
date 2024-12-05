package com.example.data.di

import com.example.data.NetworkHandler
import com.example.data.contract.MealsOfflineDataSource
import com.example.data.contract.MealsOnlineDataSource
import com.example.data.contract.SpecificMealDataSource
import com.example.data.dataSource.SpecificMealDataSourceImpl
import com.example.data.dataSource.offline.MealsOfflineDataSourceImpl
import com.example.data.dataSource.online.MealsOnlineDataSourceImpl
import com.example.data.repository.MealsRepositoryImpl
import com.example.data.repository.SpecificMealRepositoryImpl

import com.example.domain.repo.MealsRepository
import com.example.domain.repo.SpecificCategoryRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule{

    @Provides
    fun provideMealsRepo(impl:MealsRepositoryImpl):MealsRepository{
        return impl}
   @Provides
   fun provideSpecificRepo(impl:SpecificMealRepositoryImpl):SpecificCategoryRepository{
       return impl}


        @Provides
        fun provideMealsOnlineDataSource(impl: MealsOnlineDataSourceImpl):MealsOnlineDataSource{
            return impl
        }

    @Provides
    fun provideSpecificMealsDataSource(impl:SpecificMealDataSourceImpl):SpecificMealDataSource{
        return impl
    }

        @Provides
        fun provideMealsOfflineDataSource(impl: MealsOfflineDataSourceImpl):MealsOfflineDataSource{
            return impl
        }

        @Provides
        fun provideNetworkHandler():NetworkHandler{
            return object :NetworkHandler{
                override fun isOnline(): Boolean {
                    return true
                }

            }
        }
}