package com.susu.susuapp.data.network

import com.susu.susuapp.models.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("/public-api/v1.2/events/?fields=title,images")
    suspend fun getPeople(): Response<EventResponse>

}