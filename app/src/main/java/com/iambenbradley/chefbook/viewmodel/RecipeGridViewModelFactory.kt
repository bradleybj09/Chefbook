package com.iambenbradley.chefbook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class RecipeGridViewModelFactory @Inject constructor(private val recipeGridViewModel: RecipeGridViewModel) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeGridViewModel::class.java)) {
            return recipeGridViewModel as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}