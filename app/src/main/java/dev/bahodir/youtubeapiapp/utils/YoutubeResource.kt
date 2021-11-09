package dev.bahodir.youtubeapiapp.utils

import dev.bahodir.youtubeapiapp.model.YoutubeData

sealed class YoutubeResource {

    object Loading: YoutubeResource()

    data class Success(var youtubeData: YoutubeData) : YoutubeResource()

    data class Error(var message: String) : YoutubeResource()
}