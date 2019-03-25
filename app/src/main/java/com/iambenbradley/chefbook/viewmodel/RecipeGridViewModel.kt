package com.iambenbradley.chefbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iambenbradley.chefbook.model.RecipeRepository
import com.iambenbradley.chefbook.model.room.RecipeSummary
import com.iambenbradley.chefbook.retrofit.ResponseRecipeSearch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeGridViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    var searchButtonEnabled = MutableLiveData<Boolean>()
    var recipes = MutableLiveData<List<RecipeSummary>>()
    var searchQuery = ""
    lateinit var disposableObserver: DisposableObserver<ResponseRecipeSearch>

    fun recipesResult(): LiveData<List<RecipeSummary>> {
        return recipes
    }

    init {
        searchButtonEnabled.value = false
    }

    fun findRecipes() {
        disposableObserver = object : DisposableObserver<ResponseRecipeSearch>() {
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onNext(t: ResponseRecipeSearch) {
                for (recipe in t.recipes) {
                }
                recipes.postValue(t.recipes)
            }
        }

        repository.getRecipes(searchQuery)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserver)
    }
}