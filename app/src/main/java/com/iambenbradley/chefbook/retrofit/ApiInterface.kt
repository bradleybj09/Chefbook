package com.iambenbradley.chefbook.retrofit

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "Accept: Application/Json"
    )
    @GET("recipes/search")
    fun searchRecipes(@Query("query") query: String, @Query("number") number: String = "1"): Observable<ResponseRecipeSearch>

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "Accept: Application/Json"
    )
    @GET("recipes/{recipeID}/information")
    fun getRecipeDetail(@Path("recipeID") recipeID: Long): Observable<ResponseRecipeDetail>

    @Headers(
        "X-RapidAPI-Key: $API_KEY",
        "Accept: Application/Json"
    )
    @GET("recipes/search")
    fun searchRecipesCall(@Query("query") query: String, @Query("number") number: String = "1"): Call<ResponseRecipeSearch>

}