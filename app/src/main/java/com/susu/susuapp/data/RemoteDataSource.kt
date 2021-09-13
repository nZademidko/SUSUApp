package com.susu.susuapp.data

import com.susu.susuapp.data.network.NetworkApi
import com.susu.susuapp.models.EventResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val networkApi: NetworkApi
) {

    suspend fun getPeople(): Response<EventResponse> = networkApi.getPeople()

}