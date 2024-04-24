package com.getir.patika.getirlite.data.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Query("SELECT * FROM cart_items")
    suspend fun getAllProductsInCart(): List<CartProduct>

    @Insert
    suspend fun insertProduct(product: CartProduct)

    @Delete
    suspend fun deleteProduct(product: CartProduct)

    @Query("SELECT COUNT(*) FROM cart_items")
    suspend fun countProducts(): Int
}