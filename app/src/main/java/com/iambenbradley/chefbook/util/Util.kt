package com.iambenbradley.chefbook.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.iambenbradley.chefbook.model.RecipeFull
import com.iambenbradley.chefbook.model.room.Ingredient
import com.iambenbradley.chefbook.model.room.Recipe
import com.iambenbradley.chefbook.model.room.RecipeSummary
import com.iambenbradley.chefbook.retrofit.ResponseRecipeDetail
import com.squareup.picasso.Picasso

const val API_PATH = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/"
const val IMAGE_ROOT_PATH = "https://spoonacular.com/recipeImages/"


@BindingAdapter("app:fullImageUrl")
fun loadImage(view: ImageView, fullImageUrl: String?) {
    if(fullImageUrl != null) Picasso.get().load(fullImageUrl).into(view)
}

@BindingAdapter("app:shortImageUrl")
fun loadImage2(view: ImageView, shortImageUrl: String) {
    Picasso.get().load(IMAGE_ROOT_PATH + shortImageUrl).into(view)
}


fun convertRecipeListToSummaryList(input: List<Recipe>): List<RecipeSummary> {
    val list: MutableList<RecipeSummary> = ArrayList()
    for (recipe in input) {
        list.add(RecipeSummary(recipe.id, recipe.title, recipe.readyInMinutes, recipe.servings, recipe.image, listOf()))
    }
    return list
}

fun convertResponseDetailToRecipe(t: ResponseRecipeDetail): RecipeFull {
    val tempRecipe = Recipe(t.id, t.title, t.image, t.readyInMinutes, t.servings, t.instructions)
    val tempIngredients: MutableList<Ingredient> = ArrayList()
    for (ingredient in t.ingredients) {
        tempIngredients.add(
            Ingredient(
                ingredient.id,
                t.id,
                ingredient.name,
                ingredient.amount,
                ingredient.unit,
                ingredient.originalString,
                ingredient.measures.us.amount,
                ingredient.measures.us.unitShort,
                ingredient.measures.us.unitLong,
                ingredient.measures.us.amount,
                ingredient.measures.us.unitShort,
                ingredient.measures.us.unitLong
            )
        )
    }
    return RecipeFull(tempRecipe, tempIngredients)
}