package com.sapana.androidapps.spacexrocket.di.module

import com.sapana.androidapps.spacexrocket.data.network.ApiService
import com.sapana.androidapps.spacexrocket.repository.RocketRepository
import com.sapana.androidapps.spacexrocket.utils.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class AppModule {
	
	@Provides
	@Singleton
	internal fun provideRetrofit(): Retrofit {
		return Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
			.baseUrl(BASE_URL)
			.build()
	}
	
	@Provides
	@Singleton
	internal fun provideApiService(retrofit: Retrofit): ApiService {
		return retrofit.create(ApiService::class.java)
	}
	
	@Provides
	@Singleton
	internal fun getRepository(apiService: ApiService): RocketRepository {
		return RocketRepository(apiService)
	}
}