package com.example.test8.data.network

import com.example.test8.data.model.ShopItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getData() : Response<List<ShopItemDto>>
}