package com.iambenbradley.chefbook.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iambenbradley.chefbook.model.RecipeFull
import com.iambenbradley.chefbook.model.RecipeRepository
import com.iambenbradley.chefbook.model.room.Recipe
import com.iambenbradley.chefbook.retrofit.ResponseRecipeDetail
import com.iambenbradley.chefbook.util.convertResponseDetailToRecipe
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel() {

    var recipe: MutableLiveData<RecipeFull> = MutableLiveData()
    lateinit var disposableDetailObserver: DisposableObserver<ResponseRecipeDetail>
    var isFavorite = MutableLiveData<Boolean>()

    fun recipe(): LiveData<RecipeFull> {
        return recipe
    }

    fun toggleRecipeFavorite() {
        if (isFavorite.value!!) {
            repository.removeRecipeFavorite(recipe.value!!.recipe)
        } else {
            repository.addRecipeFavorite(recipe.value!!.recipe)
        }
        isFavorite.value = !(isFavorite.value as Boolean)
    }

    fun setup(recipeId: Long) {
        repository.getIsFavoriteRecipe(recipeId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :
                SingleObserver<Int> {
                override fun onError(e: Throwable) {}
                override fun onSubscribe(d: Disposable) {}
                override fun onSuccess(t: Int) {
                    isFavorite.value = t == 1
                }
            })
        disposableDetailObserver = object : DisposableObserver<ResponseRecipeDetail>() {
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
            .subscribe(disposableDetailObserver)
    }
}