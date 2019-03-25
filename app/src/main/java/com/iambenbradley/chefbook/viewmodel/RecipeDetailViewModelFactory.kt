package com.iambenbradley.chefbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class RecipeDetailViewModelFactory @Inject constructor(private val recipeDetailViewModel: RecipeDetailViewModel) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeDetailViewModel::class.java)) {
            return recipeDetailViewModel as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}