package com.getir.patika.getirlite.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
     val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://65c38b5339055e7482c12050.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}