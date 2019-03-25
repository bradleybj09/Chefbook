package com.iambenbradley.chefbook.dagger

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iambenbradley.chefbook.model.data.IngredientDao
import com.iambenbradley.chefbook.model.data.RecipeDao
import com.iambenbradley.chefbook.model.room.RecipeDatabase
import com.iambenbradley.chefbook.viewmodel.RecipeDetailViewModelFactory
import com.iambenbradley.chefbook.viewmodel.RecipeGridViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun application(): Application = app

    @Provides
    @Singleton
    fun database(): RecipeDatabase =
            Room.databaseBuilder(app, RecipeDatabase::class.java, "chefbook_db").build()

    @Provides
    @Singleton
    fun recipeDao(database: RecipeDatabase): RecipeDao = database.recipeDao()

    @Provides
    @Singleton
    fun ingredientDao(database: RecipeDatabase): IngredientDao = database.ingredientDao()

    @Provides
    fun recipeGridViewModelFactory(factory: RecipeGridViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    fun recipeDetailViewModelFactory(factory: RecipeDetailViewModelFactory): ViewModelProvider.Factory = factory
}