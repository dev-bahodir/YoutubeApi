package dev.bahodir.youtubeapiapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.bahodir.youtubeapiapp.utils.NetworkHelper

class ViewModelFactory(private var networkHelper: NetworkHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(YoutubeViewModel::class.java))
            return YoutubeViewModel(networkHelper) as T
        throw Exception("error")
    }
}