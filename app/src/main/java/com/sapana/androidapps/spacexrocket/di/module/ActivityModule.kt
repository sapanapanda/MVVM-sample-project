package com.sapana.androidapps.spacexrocket.di.module

import com.sapana.androidapps.spacexrocket.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
    abstract class ActivityModule {
        @ContributesAndroidInjector
        internal abstract fun contributeMainActivity(): MainActivity



}