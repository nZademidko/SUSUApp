package com.susu.susuapp.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("image")
    val image: String?
)