package com.susu.susuapp.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("images")
    val images: List<Image>?,
    @SerialName("title")
    val title: String?
)