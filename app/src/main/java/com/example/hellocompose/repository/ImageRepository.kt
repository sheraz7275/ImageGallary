package com.example.hellocompose.repository

import com.example.hellocompose.data.ImageItem
import com.example.hellocompose.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageRepository {
    private val api: ApiService = Retrofit.Builder()
        .baseUrl("https://picsum.photos/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    suspend fun fetchImages(): List<ImageItem> {
        return api.getImages()
    }
}
