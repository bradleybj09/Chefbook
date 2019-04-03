package com.iambenbradley.chefbook.model

import android.util.Log
import com.iambenbradley.chefbook.model.data.IngredientDao
import com.iambenbradley.chefbook.model.data.RecipeDao
import com.iambenbradley.chefbook.model.room.Recipe
import com.iambenbradley.chefbook.retrofit.ApiInterface
import com.iambenbradley.chefbook.retrofit.ResponseRecipeDetail
import com.iambenbradley.chefbook.retrofit.ResponseRecipeSearch
import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val recipeDao: RecipeDao,
    private val ingredientDao: IngredientDao
) {
    fun getFavoriteRecipes(): Observable<List<Recipe>> {
        return recipeDao.getFavoriteRecipes()

    }

    fun getIsFavoriteRecipe(recipeId: Long): Single<Int> {
        return recipeDao.getIsRecipeFavorite(recipeId)
    }

    fun addRecipeFavorite(recipe: Recipe) {
        Completable.fromAction{ recipeDao.addFavoriteRecipe(recipe) }
            .subscribeOn(Schedulers.newThread())
            .subscribe(object : CompletableObserver {
                override fun onComplete() {}
                override fun onError(e: Throwable) {}
                override fun onSubscribe(d: Disposable) {}
            })
    }

    fun removeRecipeFavorite(recipe: Recipe) {
        Completable.fromAction {recipeDao.deleteRecipe(recipe) }
            .subscribeOn(Schedulers.newThread())
            .subscribe(object : CompletableObserver {
            override fun onComplete() {}
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
        })
    }

    fun getRecipes(query: String): Observable<ResponseRecipeSearch> {
        apiInterface.searchRecipesCall(query).enqueue(object : Callback<ResponseRecipeSearch> {
            override fun onFailure(call: Call<ResponseRecipeSearch>, t: Throwable) {
                Log.e("onFailure", t.message)
            }

            override fun onResponse(call: Call<ResponseRecipeSearch>, response: Response<ResponseRecipeSearch>) {
                Log.e("onResponse", response.raw().toString())
            }
        })
        return apiInterface.searchRecipes(query)
    }

    fun getRecipeDetails(recipeId: Long): Observable<ResponseRecipeDetail> {
        return apiInterface.getRecipeDetail(recipeId)
    }
}