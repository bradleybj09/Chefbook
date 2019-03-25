package com.iambenbradley.chefbook.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseMeasures(val us: ResponseMeasure, val metric: ResponseMeasure)