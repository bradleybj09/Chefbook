package com.iambenbradley.chefbook.util

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.iambenbradley.chefbook.dagger.AppModule
import com.iambenbradley.chefbook.dagger.DaggerAppComponent
import com.iambenbradley.chefbook.dagger.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ChefbookApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule(API_PATH))
            .build().inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}