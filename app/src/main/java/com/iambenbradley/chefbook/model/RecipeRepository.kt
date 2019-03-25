package com.iambenbradley.chefbook.model

import com.iambenbradley.chefbook.model.data.IngredientDao
import com.iambenbradley.chefbook.model.data.RecipeDao
import com.iambenbradley.chefbook.model.room.Recipe
import com.iambenbradley.chefbook.retrofit.ApiInterface
import com.iambenbradley.chefbook.retrofit.ResponseRecipeDetail
import com.iambenbradley.chefbook.retrofit.ResponseRecipeSearch
import io.reactivex.Observable
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    fun getFavoriteRecipes(): Observable<List<Recipe>> {
        return recipeDao.getFavoriteRecipes()

    }

    fun getRecipes(query: String): Observable<ResponseRecipeSearch> {
        return apiInterface.searchRecipes(query)
    }

    fun getRecipeDetails(recipeId: Long): Observable<ResponseRecipeDetail> {
        return apiInterface.getRecipeDetail(recipeId)
    }
}