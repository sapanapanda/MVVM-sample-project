package com.sapana.androidapps.spacexrocket.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.view.View
import com.sapana.androidapps.spacexrocket.data.model.Rocket
import com.sapana.androidapps.spacexrocket.repository.RocketRepository
import io.reactivex.Maybe
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RocketListViewModelTest {
	
	@get:Rule
	val instantTaskExecutorRule = InstantTaskExecutorRule()
	
	
	
	@Mock
	lateinit var rocketRepository: RocketRepository
	
	lateinit var rocketListViewModel: RocketListViewModel
	@Before
	fun setUp() {
		MockitoAnnotations.initMocks(this)
		this.rocketListViewModel = RocketListViewModel(rocketRepository)
	}
	
	@Test
	fun fetchRocketList_positiveResponse() {
		Mockito.`when`(this.rocketRepository.fetchData()).thenAnswer {
			return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Rocket>())
		}
		
		val observer = Mockito.mock(Observer::class.java) as Observer<List<Rocket>>
		this.rocketListViewModel.getResponseData().observeForever(observer)
		
		this.rocketListViewModel.fetchRocketList()
		
		// Assert.assertNotNull(this.rocketListViewModel.responseLiveData.value)
		Assert.assertEquals(View.GONE,rocketListViewModel.getLoadingVisibility().value)
		Assert.assertEquals(View.VISIBLE,rocketListViewModel.getListVisibility().value)
	}
	
}