package com.iambenbradley.chefbook.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iambenbradley.chefbook.model.RecipeFull
import com.iambenbradley.chefbook.model.RecipeRepository
import com.iambenbradley.chefbook.retrofit.ResponseRecipeDetail
import com.iambenbradley.chefbook.util.convertResponseDetailToRecipe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    var expanded = false
    var recipe: MutableLiveData<RecipeFull> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<ResponseRecipeDetail>

    fun recipe(): LiveData<RecipeFull> {
        return recipe
    }

    fun setup(recipeId: Long) {

        disposableObserver = object : DisposableObserver<ResponseRecipeDetail>() {
            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onNext(t: ResponseRecipeDetail) {
                recipe.postValue(convertResponseDetailToRecipe(t))
            }
        }

        repository.getRecipeDetails(recipeId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}