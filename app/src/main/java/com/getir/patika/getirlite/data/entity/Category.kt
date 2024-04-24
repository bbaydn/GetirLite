package com.getir.patika.getirlite.data.entity

data class Category(
    val id: String,
    val name: String,
    val productCount: Int,
    val products: List<Product>
)