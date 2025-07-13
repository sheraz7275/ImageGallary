package com.example.hellocompose.network

import com.example.hellocompose.data.ImageItem
import retrofit2.http.GET

interface ApiService {
    @GET("v2/list")
    suspend fun getImages(): List<ImageItem>
}
