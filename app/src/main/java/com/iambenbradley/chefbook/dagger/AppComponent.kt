package com.iambenbradley.chefbook.dagger

import com.iambenbradley.chefbook.util.ChefbookApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, BuildersModule::class, NetModule::class])
interface AppComponent {

    fun inject(application: ChefbookApp)
}