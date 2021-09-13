package com.susu.susuapp.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.susu.susuapp.data.Repository
import com.susu.susuapp.models.EventResponse
import com.susu.susuapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var peopleResponse: MutableLiveData<NetworkResult<EventResponse>> = MutableLiveData()

    fun getPeople() = viewModelScope.launch {
        getPeopleSafeCall()
    }

    private suspend fun getPeopleSafeCall() {
        peopleResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {

            try {
                val response = repository.remote.getPeople()
                peopleResponse.value = handlePeopleResponse(response)
            } catch (e: Exception) {
                peopleResponse.value = NetworkResult.Error(e.message.toString())
            }

        } else {

            peopleResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun handlePeopleResponse(response: Response<EventResponse>): NetworkResult<EventResponse>{

       return when{
            response.message().toString().contains("timeout") ->{
                NetworkResult.Error("Timeout.")
            }
           response.code() == 402 ->{
               NetworkResult.Error("API Key Limited.")
           }
           response.body()!!.results.isNullOrEmpty() ->{
               NetworkResult.Error("Empty list")
           }
           response.isSuccessful ->{
               NetworkResult.Success(response.body())
           }
           else -> NetworkResult.Error(response.message())
       }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}