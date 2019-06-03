package com.sapana.androidapps.spacexrocket.data.network

import com.sapana.androidapps.spacexrocket.data.model.Rocket
import io.reactivex.Maybe
import retrofit2.http.GET

interface ApiService {

    @GET("rockets")
    fun getRockets(): Maybe<List<Rocket>>
}