package com.iambenbradley.chefbook.model.room

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecipeSummary(
    val id: Long,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val imageUrls: List<String>
)