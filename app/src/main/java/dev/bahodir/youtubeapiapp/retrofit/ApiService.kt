package dev.bahodir.youtubeapiapp.retrofit

import dev.bahodir.youtubeapiapp.model.YoutubeData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search?")
    suspend fun getVideos(
        @Query("key") key: String = "AIzaSyAEfWhh9Lcqv56QgG0cYzMDfCuyBNAAXbA",
        //@Query("channelId") channelId: String = "UCLRXXDGv3L_gGxUHFY8D45g",
        @Query("part") part: String = "snippet,id",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: Int = 50
    ): Response<YoutubeData>

    @GET("search?")
    suspend fun getChannelVideos(
        @Query("key") key: String = "AIzaSyAEfWhh9Lcqv56QgG0cYzMDfCuyBNAAXbA",
        @Query("channelId") channelId: String,
        @Query("part") part: String = "snippet,id",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: Int = 50
    ): Response<YoutubeData>
}