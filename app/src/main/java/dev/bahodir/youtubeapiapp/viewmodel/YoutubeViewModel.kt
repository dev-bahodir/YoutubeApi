package dev.bahodir.youtubeapiapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.bahodir.youtubeapiapp.repository.YoutubeRepository
import dev.bahodir.youtubeapiapp.retrofit.ApiClient
import dev.bahodir.youtubeapiapp.utils.NetworkHelper
import dev.bahodir.youtubeapiapp.utils.YoutubeResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class YoutubeViewModel(private var networkHelper: NetworkHelper) : ViewModel() {
    private var youtubeRepository = YoutubeRepository(ApiClient.apiService)
    private var stateFlow = MutableStateFlow<YoutubeResource>(YoutubeResource.Loading)

    init {
        getVideos()
    }

    private fun getVideos() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                youtubeRepository
                    .getVideos()
                    .catch {
                        stateFlow.value = YoutubeResource.Error(it.message?:"")
                    }
                    .collect {
                        if (it.isSuccessful && it.body() != null) {
                            stateFlow.value = YoutubeResource.Success(it.body()!!)
                        }
                        else {
                            stateFlow.value = YoutubeResource.Error(it.message())
                        }
                    }
            }
        }
        else {
            stateFlow.value = YoutubeResource.Error("Not connected to internet")
        }
    }

    fun getStateVideos() : StateFlow<YoutubeResource> {
        return stateFlow
    }
}