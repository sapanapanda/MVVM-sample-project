package com.sapana.androidapps.spacexrocket.repository

import com.sapana.androidapps.spacexrocket.data.model.Rocket
import com.sapana.androidapps.spacexrocket.data.network.ApiService
import io.reactivex.Maybe

class RocketRepository(private val apiService: ApiService) {
    /*
     * method to call rocket api
     * */
    fun fetchData(): Maybe<List<Rocket>> {
        return apiService.getRockets()
    }
}