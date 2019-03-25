package com.iambenbradley.chefbook.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseMeasure(
    val amount: Double,
    val unitShort: String,
    val unitLong: String)