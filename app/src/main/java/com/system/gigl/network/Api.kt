package com.system.gigl.network

import com.system.gigl.models.Shorts
import com.system.gigl.models.Videos
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("photos")
    suspend fun getVideos(): Response<List<Videos>>

    @GET("photos")
    suspend fun getShorts(): Response<List<Shorts>>
}