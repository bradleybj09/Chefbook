package com.iambenbradley.chefbook.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseIngredient(
    val id: Long,
    val name: String,
    val amount: Double,
    val unit: String,
    val originalString: String,
    val measures: ResponseMeasures
)