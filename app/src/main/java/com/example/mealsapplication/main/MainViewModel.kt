package com.example.mealsapplication.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.repo.MealsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MealsRepository
):ViewModel() {
    val categryList= mutableStateListOf<Category>()
     fun fetchCategoryList(){
         viewModelScope.launch {
        val meal=repository.getMeals()
        try {
            categryList.addAll(meal)
        }catch (e:Exception){
            throw e
        }
         }
    }

}