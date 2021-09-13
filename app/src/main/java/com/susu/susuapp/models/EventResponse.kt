package com.susu.susuapp.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    @SerialName("results")
    val results: List<Result>?
)