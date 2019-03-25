package com.iambenbradley.chefbook.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe"
)
data class Recipe(
    @PrimaryKey
    val id: Long,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val instructions: String
)