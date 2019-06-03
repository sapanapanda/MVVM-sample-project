package com.sapana.androidapps.spacexrocket.data.model

data class SecondStage(
    val burn_time_sec: Any,
    val engines: Int,
    val fuel_amount_tons: Float,
    val payloads: Payloads,
    val reusable: Boolean,
    val thrust: Thrust
)