package com.getir.patika.getirlite.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cart_items")
data class CartProduct(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val price: Double?,
    val attributes: String?,
    val imageURL: String?,
    val totalPrice: Int?
)
