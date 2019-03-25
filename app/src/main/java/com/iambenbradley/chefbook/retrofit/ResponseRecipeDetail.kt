package com.iambenbradley.chefbook.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseRecipeDetail(
    val id: Long,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val instructions: String,
    @Json(name = "extendedIngredients")
    val ingredients: List<ResponseIngredient>
)