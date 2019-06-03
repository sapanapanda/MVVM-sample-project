package com.sapana.androidapps.spacexrocket.di

import android.app.Application
import com.sapana.androidapps.spacexrocket.AppController
import com.sapana.androidapps.spacexrocket.di.module.ActivityModule
import com.sapana.androidapps.spacexrocket.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent {
	@Component.Builder
	interface Builder {
		// provide Application instance into DI
		@BindsInstance
		fun application(application: Application): Builder
		
		fun build(): AppComponent
	}
	
	// this is needed because LuaApp has @Inject
	fun inject(appController: AppController)
}
