package com.sapana.androidapps.spacexrocket.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.sapana.androidapps.spacexrocket.R
import com.sapana.androidapps.spacexrocket.data.model.Rocket
import com.sapana.androidapps.spacexrocket.repository.RocketRepository
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RocketListViewModel @Inject constructor(
	private val rocketRepository: RocketRepository
) : ViewModel() {
	private val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
	private val listVisibility: MutableLiveData<Int> = MutableLiveData()
	private val errorMessage: MutableLiveData<Int> = MutableLiveData()
	private val responseLiveData = MutableLiveData<List<Rocket>>()
	
	
	fun fetchRocketList() {
		loadingVisibility.postValue(View.VISIBLE)
		rocketRepository.fetchData().subscribe(GetListConsumer())
	}
	
	
	/*
	 * rocketRepository.fetchData() Observer
	 */
	inner class GetListConsumer : MaybeObserver<List<Rocket>> {
		override fun onSubscribe(d: Disposable) {
			this@RocketListViewModel.loadingVisibility.postValue(View.VISIBLE)
			errorMessage.postValue(null)
			
		}
		
		override fun onError(e: Throwable) {
			this@RocketListViewModel.errorMessage.postValue(R.string.error)
			this@RocketListViewModel.loadingVisibility.postValue(View.GONE)
			this@RocketListViewModel.listVisibility.postValue(View.GONE)
		}
		
		override fun onSuccess(t: List<Rocket>) {
			this@RocketListViewModel.responseLiveData.postValue(getSortedList(t))
			this@RocketListViewModel.loadingVisibility.postValue(View.GONE)
			this@RocketListViewModel.listVisibility.postValue(View.VISIBLE)
		}
		
		override fun onComplete() {
			this@RocketListViewModel.loadingVisibility.postValue(View.GONE)
			this@RocketListViewModel.listVisibility.postValue(View.VISIBLE)
		}
	}
	
	
	private fun getSortedList(rocketList: List<Rocket>): List<Rocket> {
		return rocketList.sortedWith(compareBy { it.height.feet })
	}
	
	
	fun getListVisibility() = listVisibility
	fun getLoadingVisibility() = loadingVisibility
	fun getResponseData() = responseLiveData
	fun getError() = errorMessage
	
	fun getErrorClick() = View.OnClickListener { fetchRocketList() }
	
	
}



