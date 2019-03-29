package com.iambenbradley.chefbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iambenbradley.chefbook.model.RecipeRepository
import com.iambenbradley.chefbook.model.room.Recipe
import com.iambenbradley.chefbook.model.room.RecipeSummary
import com.iambenbradley.chefbook.retrofit.ResponseRecipeSearch
import com.iambenbradley.chefbook.util.convertRecipeListToSummaryList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeGridViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    var searchButtonEnabled = MutableLiveData<Boolean>()
    var recipes = MutableLiveData<List<RecipeSummary>>()
    var searchQuery = ""
    lateinit var disposableSearchObserver: DisposableObserver<ResponseRecipeSearch>
    lateinit var disposableFavoritesObserver: DisposableObserver<List<Recipe>>
    var showingFavorites = MutableLiveData<Boolean>()

    fun recipesResult(): LiveData<List<RecipeSummary>> {
        return recipes
    }

    init {
        searchButtonEnabled.value = false
        showingFavorites.value = true
        getFavoriteRecipes()
    }

    fun findRecipes() {
        disposableSearchObserver = object : DisposableObserver<ResponseRecipeSearch>() {
            override fun onComplete() {}
            override fun onError(e: Throwable) {}
            override fun onNext(t: ResponseRecipeSearch) {
                for (recipe in t.recipes) {
                }
                recipes.postValue(t.recipes)
                showingFavorites.value = false
            }
        }

        repository.getRecipes(searchQuery)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableSearchObserver)
    }

    fun getFavoriteRecipes() {
        disposableFavoritesObserver = object : DisposableObserver<List<Recipe>>() {
            override fun onComplete() {}
            override fun onError(e: Throwable) {}
            override fun onNext(t: List<Recipe>) {
                recipes.postValue(convertRecipeListToSummaryList(t))
                showingFavorites.value = true
            }
        }
        repository.getFavoriteRecipes()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableFavoritesObserver)
    }
}