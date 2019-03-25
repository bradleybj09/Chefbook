package com.iambenbradley.chefbook.model.room

import androidx.room.Entity

@Entity(
    tableName = "ingredient",
    primaryKeys = ["id","recipeId"]
)
data class Ingredient(
    val id: Long,
    val recipeId: Long,
    val name: String,
    val amount: Double,
    val unit: String,
    val originalString: String,
    val usAmount: Double,
    val usUnitShort: String,
    val usUnitLong: String,
    val metricAmount: Double,
    val metricUnitShort: String,
    val metricUnitLong: String
)