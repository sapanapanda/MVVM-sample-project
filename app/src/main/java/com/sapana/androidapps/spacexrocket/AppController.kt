package com.sapana.androidapps.spacexrocket

import android.app.Activity
import android.app.Application
import com.sapana.androidapps.spacexrocket.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


    class AppController : Application(), HasActivityInjector {
        @Inject
        lateinit var activityInjector: DispatchingAndroidInjector<Activity>

        override fun onCreate() {
            super.onCreate()
            // initialize Dagger
            DaggerAppComponent.builder().application(this).build().inject(this)
        }

        // this is required to setup Dagger2 for Activity
        override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    }
