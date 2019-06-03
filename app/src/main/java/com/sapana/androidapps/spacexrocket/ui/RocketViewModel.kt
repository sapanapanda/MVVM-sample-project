package com.sapana.androidapps.spacexrocket.ui

import android.arch.lifecycle.MutableLiveData
import com.sapana.androidapps.spacexrocket.data.model.Rocket

class RocketViewModel {
    private val title = MutableLiveData<String>()
    private val heightFeet = MutableLiveData<String>()
    private val heightMeter = MutableLiveData<String>()

    fun bind(rocket: Rocket) {
        title.value = rocket.rocket_name
        heightFeet.value = "Height(Feet) : " + rocket.height.feet
        heightMeter.value = "Height(Meters) : " + rocket.height.meters
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getHeightFeet(): MutableLiveData<String> {
        return heightFeet
    }

    fun getHeightMeters(): MutableLiveData<String> {
        return heightMeter
    }
}