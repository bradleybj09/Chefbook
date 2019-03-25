package com.iambenbradley.chefbook.model.data

import androidx.room.Dao
import androidx.room.Query
import com.iambenbradley.chefbook.model.room.Recipe
import io.reactivex.Observable

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getFavoriteRecipes(): Observable<List<Recipe>>
}