package com.getir.patika.getirlite.data.repository

import android.util.Log
import com.getir.patika.getirlite.data.entity.Product
import com.getir.patika.getirlite.data.retrofit.ApiService


class ProductRepository(private val apiService: ApiService) {

    suspend fun getProducts(): List<Product>? {
        val response = apiService.getAllProducts()
        if (response.isSuccessful) {
            val categories = response.body()
            if (categories != null) {
                val allProducts = mutableListOf<Product>()
                for (category in categories) {
                    category.products?.let {
                        allProducts.addAll(it)
                    }
                }
                return allProducts
            }
        } else {
            Log.e("ProductRepository", "Error fetching products: ${response.errorBody()?.string()}")
        }
        return null
    }

    suspend fun getSuggestedProducts(): List<Product>? {
        val response = apiService.getSuggestedProducts()
        if (response.isSuccessful) {
            val suggestions = response.body()
            if (!suggestions.isNullOrEmpty()) {
                return suggestions[0].products
            }
            return response.body()
        } else {
            Log.e("ProductRepository", "Error fetching suggested products: ${response.errorBody()?.string()}")
        }
        return null
    }


}
