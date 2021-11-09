package dev.bahodir.youtubeapiapp.repository

import dev.bahodir.youtubeapiapp.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class YoutubeRepository(private var apiService: ApiService) {
    suspend fun getVideos() = flow { emit(apiService.getVideos()) }
}