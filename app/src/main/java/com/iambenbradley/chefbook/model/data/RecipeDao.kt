package com.iambenbradley.chefbook.model.data

import androidx.room.*
import com.iambenbradley.chefbook.model.room.Recipe
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getFavoriteRecipes(): Observable<List<Recipe>>

    @Query("SELECT COUNT(*) FROM recipe WHERE id = :recipeId")
    fun getIsRecipeFavorite(recipeId: Long): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)
}