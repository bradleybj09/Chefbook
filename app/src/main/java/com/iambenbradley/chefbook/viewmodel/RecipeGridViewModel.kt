package com.iambenbradley.chefbook.viewmodel

import androidx.lifecycle.ViewModel
import com.iambenbradley.chefbook.model.RecipeRepository
import javax.inject.Inject

class RecipeGridViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {


}