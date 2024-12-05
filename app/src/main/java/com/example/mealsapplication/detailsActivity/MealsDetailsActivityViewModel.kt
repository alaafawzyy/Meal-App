package com.example.mealsapplication.detailsActivity

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Category
import com.example.domain.repo.SpecificCategoryRepository
import com.example.domain.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsDetailsActivityViewModel @Inject constructor(
    private val repository: SpecificCategoryRepository
) : ViewModel() {

    val categryLiveData = MutableLiveData<Meal?>()

    fun getSpecificMeals(id: String) {
        viewModelScope.launch {
            try {
                val meal = repository.getSpecificCategory(id)
                if (meal != null) {
                    categryLiveData.postValue(meal)
                } else {
                    Log.e("MealsDetailsActivityViewModel", "Meal not found")
                }
            } catch (e: Exception) {
                Log.e("MealsDetailsActivityViewModel", "Error fetching meal: ${e.message}")
            }
        }
    }
}
