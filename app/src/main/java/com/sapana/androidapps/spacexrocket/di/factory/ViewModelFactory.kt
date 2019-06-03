package com.sapana.androidapps.spacexrocket.di.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sapana.androidapps.spacexrocket.ui.RocketListViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
	private val rocketListViewModel: RocketListViewModel
) : ViewModelProvider.Factory {
	
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(RocketListViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return rocketListViewModel as T
		}
		throw IllegalArgumentException("Unknown class name")
	}
}