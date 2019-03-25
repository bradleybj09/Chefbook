package com.iambenbradley.chefbook.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iambenbradley.chefbook.model.data.IngredientDao
import com.iambenbradley.chefbook.model.data.RecipeDao

@Database(entities = [Recipe::class, Ingredient::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
}