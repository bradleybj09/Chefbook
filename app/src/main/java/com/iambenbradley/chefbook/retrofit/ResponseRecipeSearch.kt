package com.iambenbradley.chefbook.retrofit

import com.iambenbradley.chefbook.model.room.RecipeSummary
import com.squareup.moshi.Json

data class ResponseRecipeSearch(@Json(name = "results") val recipes: List<RecipeSummary>)