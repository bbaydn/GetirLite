package com.getir.patika.getirlite.data.repository

import com.getir.patika.getirlite.data.entity.CartDao
import com.getir.patika.getirlite.data.entity.CartProduct

class CartRepository(private val cartDao: CartDao) {
    suspend fun insertProduct(product: CartProduct) {
        cartDao.insertProduct(product)
    }

    suspend fun deleteProduct(product: CartProduct){
        cartDao.deleteProduct(product)
    }

    suspend fun getAllProduct(): List<CartProduct> {
        return cartDao.getAllProductsInCart()
    }
}
