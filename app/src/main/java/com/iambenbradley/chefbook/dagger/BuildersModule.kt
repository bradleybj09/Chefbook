package com.iambenbradley.chefbook.dagger

import com.iambenbradley.chefbook.view.ui.MainActivity
import com.iambenbradley.chefbook.view.ui.RecipeDetailFragment
import com.iambenbradley.chefbook.view.ui.RecipeGridFragment
import com.iambenbradley.chefbook.view.ui.ShoppingListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeRecipeGridFragment(): RecipeGridFragment

    @ContributesAndroidInjector
    abstract fun contributeRecipeDetailFragment(): RecipeDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeShoppingListFragment(): ShoppingListFragment

}