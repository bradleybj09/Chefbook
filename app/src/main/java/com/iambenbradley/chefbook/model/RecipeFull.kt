package com.iambenbradley.chefbook.model

import com.iambenbradley.chefbook.model.room.Ingredient
import com.iambenbradley.chefbook.model.room.Recipe

data class RecipeFull (
    val recipe: Recipe,
    val ingredients: List<Ingredient>
)