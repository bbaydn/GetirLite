package com.getir.patika.getirlite.retrofit

import com.getir.patika.getirlite.data.entity.Category
import com.getir.patika.getirlite.data.entity.Product
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<Category>>

    @GET("suggestedProducts")
    suspend fun getSuggestedProducts(): Response<List<Product>>
}